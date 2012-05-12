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
public class Artist {
    @Id @GeneratedValue
    private long id;
    
    @Column
    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
