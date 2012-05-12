package com.musitude.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Author: Iurii Lytvynenko
 */
@Entity
public class Event {
    @Id @GeneratedValue
    private long id;
    @Column
    private double latitude;
    @Column
    private double longitude;

    public Event(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
