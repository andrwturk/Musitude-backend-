package com.musitude.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * TODO: Document me
 * Author: Iurii Lytvynenko
 */
public class EM {
    private static EntityManagerFactory factory;

    private EM() { }

    public static EntityManager get() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("com.musitude.jpa");
        }
        return factory.createEntityManager();
    }
}
