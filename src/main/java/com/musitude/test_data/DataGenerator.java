package com.musitude.test_data;

import com.musitude.jpa.EM;
import com.musitude.model.Event;
import com.musitude.model.Venue;

import javax.persistence.EntityManager;

/**
 * Author: Iurii Lytvynenko
 * DNO bit-cafe: 50.453842721468185, 30.44959545135498
 * Ishvara Yoga center: 50.45083,30.451078
 */
public class DataGenerator {
    public void generateData() {
        EntityManager em = EM.get();
        try {
            em.getTransaction().begin();
            Venue dno = new Venue("DNO bit-cafe", 50.453842721468185, 30.44959545135498);
            Venue yogaCenter = new Venue("Yoga center", 50.45083, 30.451078);
            em.merge(new Event(dno));
            em.merge(new Event(yogaCenter));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
