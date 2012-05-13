package com.musitude.rest.dto;

import com.musitude.model.CheckIn;
import com.musitude.model.Event;
import com.musitude.model.Event.Status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Author: Iurii Lytvynenko
 */
public class EventDetailsDto {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private long id;

    private long venueId;
    private long artistId;
    private String venueName;
    private String artistName;
    private String startDate;
    private String endDate;
    private Status status;
    private int guests;

    private int likes;
    private String action;

    public EventDetailsDto(Event event, String action) {
        this.id = event.getId();
        this.venueId = event.getVenue().getId();
        this.venueName = event.getVenue().getName();
        this.artistId = event.getArtist().getId();
        this.artistName = event.getArtist().getName();
        this.startDate = df.format(event.getStartDate());
        this.endDate = df.format(event.getEndDate());
        this.status = event.getStatus();
        this.action = action;
        calcGuestsAndLikes(event);
    }

    private void calcGuestsAndLikes(Event event) {
        guests = event.getCheckIns().size();
        likes = 0;
        for (CheckIn check : event.getCheckIns()) {
            if (check.getStatus() == CheckIn.Status.LIKED)
                likes++;
        }
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
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

    public String getAction() {
        return action;
    }
}

