package com.musitude.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
@Entity
public class CheckIn {
    public enum Mood {
        LIKE, DISLIKE
    }
    
    @Id @GeneratedValue
    private long id;
    
    @Column
    private String deviceId;
    
    @Column
    private Mood mood;

    @ManyToOne
    private Event event;
    
    public CheckIn() {
    }

    public CheckIn(String deviceId, Event event) {
        this.deviceId = deviceId;
        this.event = event;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
