package com.musitude.rest;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class LocationSpan {
    private float latitude;
    private float longitude;
    
    private float latitudeSpan;
    private float longitudeSpan;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitudeSpan() {
        return latitudeSpan;
    }

    public void setLatitudeSpan(float latitudeSpan) {
        this.latitudeSpan = latitudeSpan;
    }

    public float getLongitudeSpan() {
        return longitudeSpan;
    }

    public void setLongitudeSpan(float longitudeSpan) {
        this.longitudeSpan = longitudeSpan;
    }
}
