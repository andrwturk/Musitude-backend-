package com.musitude.rest;

import com.musitude.jpa.EM;
import com.musitude.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
@Path("event")
public class EventResource {
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public List<Event> getEvents(@QueryParam("area") String areaSource) {
        EntityManager em = EM.get();
        LocationSpan area = JsonUtil.read(areaSource, LocationSpan.class);
        try {

            String qlString = "SELECT e FROM Event e WHERE e.latitude " +
                                      "BETWEEN :lat - :lat_span AND :lat + :lat_span " +
                                      "AND e.longitude BETWEEN :long - :long_span AND :long + :long_span";
            TypedQuery<Event> query = em.createQuery(qlString, Event.class);
            query.setParameter("lat", area.getLatitude());
            query.setParameter("lat_span", area.getLatitudeSpan());
            query.setParameter("long", area.getLongitude());
            query.setParameter("long_span", area.getLongitudeSpan());
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
