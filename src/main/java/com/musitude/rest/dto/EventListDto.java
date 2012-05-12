package com.musitude.rest.dto;

import com.musitude.model.Event;

/**
 * Author: Iurii Lytvynenko
 */
public class EventListDto {
    private long id;
    private double latitude;
    private double longitude;

    public EventListDto(Event event) {
        this.id = event.getId();
        this.latitude = event.getVenue().getLatitude();
        this.longitude = event.getVenue().getLongitude();
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
    
}
