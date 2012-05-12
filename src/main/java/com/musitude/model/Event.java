package com.musitude.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Author: Iurii Lytvynenko
 */
@Entity
public class Event {
    @Id @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Venue venue;
    
    public Event(Venue venue) {
        this.venue = venue;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
