package com.musitude.rest;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.musitude.jpa.EM;
import com.musitude.model.Event;
import com.musitude.rest.dto.EventListDto;
import com.sun.jersey.api.NotFoundException;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
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
public class EventsResource {
            
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

    private Event getEvent(long id, EntityManager em) {
        Event event;
        event = em.find(Event.class, id);
        if (event == null)
            throw new NotFoundException("Entity not found: " + id);
        Hibernate.initialize(event.getCheckIns());
        return event;
    }

    @Path("{id}/{deviceId}")
    public EventDetailsResource eventResource(@PathParam("id") long id, @PathParam("deviceId") String deviceId) {
        EntityManager em = EM.get();
        try {
            return new EventDetailsResource(getEvent(id, em), deviceId);
        } finally {
            em.close();
        }
    }
}
