package com.musitude.test_data;

import com.musitude.jpa.EM;
import com.musitude.model.Event;

import javax.persistence.EntityManager;

/**
 * Author: Iurii Lytvynenko
 * DNO bit-cafe: 50.453842721468185, 30.44959545135498
 * Ishvara Joga center: 50.45083,30.451078
 */
public class DataGenerator {
    public void generateData() {
        EntityManager em = EM.get();
        try {
            em.getTransaction().begin();
            em.merge(new Event(50.453842721468185, 30.44959545135498));
            em.merge(new Event(50.45083, 30.451078));
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }
}
