package com.musitude.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
@Entity
public class Venue {
    @Id @GeneratedValue
    private long id;

    @Column
    private String name;
    
    @Column
    private double latitude;
    
    @Column
    private double longitude;

    public Venue(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Venue() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
