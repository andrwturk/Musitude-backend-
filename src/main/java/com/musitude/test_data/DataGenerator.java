package com.musitude.test_data;

import com.musitude.jpa.EM;
import com.musitude.model.Artist;
import com.musitude.model.CheckIn;
import com.musitude.model.CheckIn.Status;
import com.musitude.model.Event;
import com.musitude.model.Venue;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Author: Iurii Lytvynenko
 * DNO bit-cafe: 50.453842721468185, 30.44959545135498
 * Ishvara Yoga center: 50.45083,30.451078
 */
public class DataGenerator {
    public void generateData() {
        EntityManager em = EM.get();
        try {
            if (hasData(em))
                return;
            em.getTransaction().begin();
            // Venues
            Venue dno = new Venue("DNO bit-cafe", 50.453842721468185, 30.44959545135498);
            Venue yogaCenter = new Venue("Yoga center", 50.45083, 30.451078);
            
            // Artists
            Artist hariBand = new Artist("Hari band");
            Artist sandBand = new Artist("Sand send");
            
            // Events
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            em.merge(new Event(dno, sandBand, format.parse("2012-05-13 17:00"), format.parse("2012-05-13 21:00")));
            Event yogaEvent = new Event(yogaCenter, hariBand, format.parse("2012-05-19 20:00"), 
                                        format.parse("2012-05-19 23:00"));
            yogaEvent.getCheckIns().add(new CheckIn("device1", yogaEvent));
            CheckIn like = new CheckIn("device2", yogaEvent);
            like.setStatus(Status.LIKED);
            yogaEvent.getCheckIns().add(like);
            em.merge(yogaEvent);
            em.getTransaction().commit();
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        } finally {
            em.close();
        }

    }

    private boolean hasData(EntityManager em) {
        return em.find(Event.class, 1l) != null;
    }
}
