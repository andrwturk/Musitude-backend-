package com.musitude.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Iurii Lytvynenko
 */
@Entity
public class Event {
    public enum Status {
        FUTURE, ACTIVE, PAST
    }

    @Id @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Venue venue;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Artist artist;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.MERGE)
    private Set<CheckIn> checkIns;

    public Event(Venue venue, Artist artist, Date startDate, Date endDate) {
        this.venue = venue;
        this.artist = artist;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Artist getArtist() {
        return artist;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Set<CheckIn> getCheckIns() {
        if (checkIns == null) {
            checkIns = new HashSet<CheckIn>();
        }
        return checkIns;
    }

    public Status getStatus() {
        Date current = new Date();
        if (startDate.after(current))
            return Status.FUTURE;
        else if (endDate.after(current))
            return Status.ACTIVE;
        else
            return Status.PAST;
    }
    
    public  boolean isActive() {
        return getStatus() == Status.ACTIVE;
    }

}
