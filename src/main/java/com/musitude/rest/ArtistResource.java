package com.musitude.rest;

import com.musitude.jpa.EM;
import com.musitude.model.Artist;
import com.sun.jersey.api.NotFoundException;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Author: Iurii Lytvynenko
 */
@Path("artist")
@Produces(MediaType.APPLICATION_JSON)
public class ArtistResource {

    @GET
    @Path("{id}")
    public Artist get(@PathParam("id") long id) {
        EntityManager em = EM.get();
        try {
            Artist artist = em.find(Artist.class, id);
            if (artist == null)
                throw new NotFoundException("Entity not found: " + id);
            return artist;
        } finally {
            em.close();
        }
    }
}
