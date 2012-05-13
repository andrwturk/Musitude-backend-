package com.musitude.rest.dto;

import com.musitude.model.Event;

/**
 * Author: Iurii Lytvynenko
 */
public class EventListDto {
    private long id;
    private double latitude;
    private double longitude;
    private String venueName;
    private String artistName;

    public EventListDto(Event event) {
        this.id = event.getId();
        this.latitude = event.getVenue().getLatitude();
        this.longitude = event.getVenue().getLongitude();
        this.venueName = event.getVenue().getName();
        this.artistName = event.getArtist().getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getArtistName() {
        return artistName;
    }
}
