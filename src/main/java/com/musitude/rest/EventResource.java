package com.musitude.rest;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.musitude.jpa.EM;
import com.musitude.model.CheckIn;
import com.musitude.model.CheckIn.Mood;
import com.musitude.model.Event;
import com.musitude.rest.dto.EventDetailsDto;
import com.musitude.rest.dto.EventListDto;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
@Path("event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
    @SuppressWarnings("unchecked")
    @GET
    public Collection<EventListDto> getEvents() {
        EntityManager em = EM.get();
        try {

            String qlString = "SELECT e FROM Event e";
            TypedQuery<Event> query = em.createQuery(qlString, Event.class);
            List<Event> events = query.getResultList();

            return Collections2.transform(events, new Function<Event, EventListDto>() {
                public EventListDto apply(Event event) {
                    return new EventListDto(event);
                }
            });
        } finally {
            em.close();
        }
    }

    @GET
    @Path("{id}")
    public EventDetailsDto getEventDetails(@PathParam("id") long id) {
        EntityManager em = EM.get();
        try {
            return new EventDetailsDto(getEvent(id, em));
        } finally {
            em.close();
        }
    }

    private Event getEvent(long id, EntityManager em) {
        Event event;
        event = em.find(Event.class, id);
        if (event == null)
            throw new NotFoundException("Entity not found: " + id);
        return event;
    }

    @POST
    @Path("{id}/{deviceId}")
    public void checkIn(@PathParam("id") long id, @PathParam("deviceId") String deviceId) {
        EntityManager em = EM.get();
        try {
            em.getTransaction().begin();
            Event event = getEvent(id, em);
            CheckIn in = findCheckIn(event, deviceId);
            if (in != null) {
                throw new ConflictException("The device is already checked in to the event");
            }
            em.merge(new CheckIn(deviceId, event));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @PUT
    @Path("{id}/{deviceId}/!")
    public void like(@PathParam("id") long id, @PathParam("deviceId") String deviceId) {
        setMood(id, deviceId, Mood.LIKE);
    }
    
    @PUT
    @Path("{id}/{deviceId}/-")
    public void dislike(@PathParam("id") long id, @PathParam("deviceId") String deviceId) {
        setMood(id, deviceId, Mood.DISLIKE);
    }

    private void setMood(long id, String deviceId, Mood mood) {
        EntityManager em = EM.get();
        try {
            em.getTransaction().begin();
            Event event = getEvent(id, em);
            CheckIn in = findCheckIn(event, deviceId);
            if (in == null) {
                throw new ConflictException("Check in first");
            }
            in.setMood(mood);
            em.merge(in);
            em.getTransaction().commit();
        } finally {
            em.close();
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
