package com.musitude.rest;

import com.google.common.io.Files;
import com.musitude.Application;
import com.musitude.jpa.EM;
import com.musitude.model.Artist;
import com.sun.jersey.api.NotFoundException;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;

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
    
    @GET
    @Path("{id}/logo")
    @Produces("image/png")
    public byte[] getLogo(@PathParam("id") long id) {
        String imageName = "artist-" + id + ".png";
        try {
            return Files.toByteArray(new File(Application.getMediaFolderPath(), imageName));
        } catch (IOException e) {
            throw new NotFoundException("Artist logo can't be found: " + id);
        }
    }
}
