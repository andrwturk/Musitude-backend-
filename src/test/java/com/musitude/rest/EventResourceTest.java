package com.musitude.rest;

import com.sun.jersey.api.client.WebResource;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Author: Iurii Lytvynenko
 * 
 * Current location: 50.449566, 30.458887
 * DNO bit-cafe: 50.453842721468185, 30.44959545135498
 * Ishvara Joga center: 50.45083,30.451078
 */
public class EventResourceTest extends RestTestSupport {

    @Test
    public void testEventsFound() throws Exception {
        // Givan
        WebResource resource = resource();
        String span = "{\"latitude\": 50.449566, \"longitude\": 30.458887, \"latitudeSpan\": 1, \"longitudeSpan\": 1}";
        WebResource path = resource.path("event").queryParam("area", span);
        
        // When
        String events = path.get(String.class);
        
        // Then
        assertThat(events).startsWith("[{");
    }
    
    @Test
    public void testEventsNotFound() throws Exception {
        // Givan
        WebResource resource = resource();
        String span = "{\"latitude\": 50.449566, \"longitude\": 30.458887, \"latitudeSpan\": 0.001, \"longitudeSpan\": 1}";
        WebResource path = resource.path("event").queryParam("area", span);
        
        // When
        String events = path.get(String.class);
        
        // Then
        assertThat(events).isEqualTo("[]"); // Nothing found
    }
}
