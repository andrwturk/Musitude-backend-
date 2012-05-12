package com.musitude.test_data;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: Iurii Lytvynenko
 */
public class DataGenerationListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        DataGenerator dg = new DataGenerator();
        dg.generateData();

    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
