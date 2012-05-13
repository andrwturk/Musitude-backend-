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
    public enum Status {
        WILL_GO, CHECKED_IN, LIKED
    }
    
    @Id @GeneratedValue
    private long id;
    
    @Column
    private String deviceId;
    
    @Column
    private Status status;

    @ManyToOne
    private Event event;
    
    public CheckIn() {
    }

    public CheckIn(String deviceId, Event event) {
        this.deviceId = deviceId;
        this.event = event;
        this.status = Status.WILL_GO;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
