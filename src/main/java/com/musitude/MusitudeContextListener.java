package com.musitude;

import com.musitude.test_data.DataGenerator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Author: Iurii Lytvynenko
 */
public class MusitudeContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        DataGenerator dg = new DataGenerator();
        dg.generateData();
        Application.setMediaFolderPath(sce.getServletContext().getInitParameter("media-folder"));
        if (Application.getMediaFolderPath() == null)
            throw new IllegalStateException("Media folder path must be set up as a context param media-folder");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing
    }
}
