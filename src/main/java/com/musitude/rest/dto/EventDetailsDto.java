package com.musitude.rest.dto;

import com.musitude.model.CheckIn;
import com.musitude.model.CheckIn.Mood;
import com.musitude.model.Event;

import java.util.Date;

/**
 * Author: Iurii Lytvynenko
 */
public class EventDetailsDto {
    public enum Status {
        FUTURE, ACTIVE, PAST
    }
    private long id;
    private long venueId;
    private long artistId;
    private String venueName;
    private String artistName;
    private Date startDate;
    private Date endDate;
    private Status status;
    
    private int guests;
    private int likes;

    public EventDetailsDto(Event event) {
        this.id = event.getId();
        this.venueId = event.getVenue().getId();
        this.venueName = event.getVenue().getName();
        this.artistId = event.getArtist().getId();
        this.artistName = event.getArtist().getName();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        guests = event.getCheckIns().size();
        likes = 0;
        for (CheckIn check : event.getCheckIns()) {
            if (check.getMood() == Mood.LIKE)
                likes++;
        }

        Date current = new Date();
        if (startDate.after(current))
            status = Status.FUTURE;
        else if (endDate.after(current))
            status = Status.ACTIVE;
        else 
            status = Status.PAST;
    }

    public long getId() {
        return id;
    }

    public long getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public long getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getGuests() {
        return guests;
    }

    public int getLikes() {
        return likes;
    }

    public Status getStatus() {
        return status;
    }
}

