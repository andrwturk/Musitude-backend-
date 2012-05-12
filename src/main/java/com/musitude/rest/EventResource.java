package com.musitude.rest;

import com.musitude.jpa.EM;
import com.musitude.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public List<Event> getEvents() {
        EntityManager em = EM.get();
        try {

            String qlString = "SELECT e FROM Event e";
            Query query = em.createQuery(qlString);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("{id}")
    public Event getEvent(@PathParam("id") long id) {
        EntityManager em = EM.get();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }
}
