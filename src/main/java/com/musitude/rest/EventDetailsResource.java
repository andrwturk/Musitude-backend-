package com.musitude.rest;

import com.musitude.jpa.EM;
import com.musitude.model.CheckIn;
import com.musitude.model.CheckIn.Status;
import com.musitude.model.Event;
import com.musitude.rest.dto.EventDetailsDto;
import com.sun.jersey.api.Responses;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;

import static com.musitude.model.Event.Status.PAST;

/**
 * Author: Iurii Lytvynenko
 */
@Produces(MediaType.APPLICATION_JSON)
public class EventDetailsResource {
    private Event event;
    private String deviceId;

    private static final String ACTION_WILL_GO = "will_go",
            ACTION_CHECK_IN = "check_in",
            ACTION_LIKE = "like",
            ACTION_NONE = "";


    public EventDetailsResource(Event event, String deviceId) {
        this.deviceId = deviceId;
        this.event = event;
    }

    @GET
    @Path("")
    public EventDetailsDto getEventDetails() {
        return new EventDetailsDto(event, deriveAction(event, deviceId));
    }

    private String deriveAction(Event event, String deviceId) {
        CheckIn checkIn = findCheckIn(event, deviceId);
        if (event.getStatus() == PAST)
            return ACTION_NONE;
        if (checkIn == null)
            return event.isActive() ? ACTION_CHECK_IN : ACTION_WILL_GO;
        switch (checkIn.getStatus()) {
            case WILL_GO:
                return event.isActive() ? ACTION_CHECK_IN : ACTION_NONE;
            case CHECKED_IN:
                return ACTION_LIKE;
            case LIKED:
            default:
                return ACTION_NONE;
        }
    }

    @POST
    @Path(ACTION_WILL_GO)
    public void willGo() {
        performAction(ACTION_WILL_GO, Status.WILL_GO);
    }

    @POST
    @Path(ACTION_CHECK_IN)
    public void checkIn() {
        performAction(ACTION_CHECK_IN, Status.CHECKED_IN);
    }

    @POST
    @Path(ACTION_LIKE)
    public void like() {
        performAction(ACTION_LIKE, Status.LIKED);
    }

    private void performAction(String action, Status nextStatus) {
        EntityManager em = EM.get();
        try {
            em.getTransaction().begin();
            validateAction(event, deviceId, action);

            CheckIn in = findCheckIn(event, deviceId);
            if (in == null) {
                in = new CheckIn(deviceId, event);
            }
            in.setStatus(nextStatus);
            em.merge(in);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validateAction(Event event, String deviceId, String actionCheckIn) {
        String allowedAction = deriveAction(event, deviceId);
        if (!allowedAction.equals(actionCheckIn)) {
            ResponseBuilder responseBuilder =
                    Responses.notAcceptable()
                             .entity("Action can't be performed in the given context: " + actionCheckIn)
                             .type("text/plain");
            throw new WebApplicationException(responseBuilder.build());
        }
    }

    private CheckIn findCheckIn(Event event, String deviceId) {
        for (CheckIn in : event.getCheckIns()) {
            if (in.getDeviceId().equals(deviceId)) {
                return in;
            }
        }
        return null;
    }
}
