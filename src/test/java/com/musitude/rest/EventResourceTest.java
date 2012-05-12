package com.musitude.rest;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

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
        WebResource path = resource.path("event");
        
        // When
        String events = path.get(String.class);
        
        // Then
        assertThat(events).startsWith("[{");
    }

    @Test
    public void testGetEvent() throws Exception {
        // Given
        WebResource resource = resource().path("event").path("1");
        // When
        String event = resource.get(String.class);
        
        // Then
        assertThat(event).contains("\"id\":1");
    }
    
    @Test
    public void testGetNonExistingEvent() throws Exception {
        // Given
        WebResource resource = resource().path("event").path("100");
        // When
        try {

            resource.get(String.class);
            fail();
        } catch (UniformInterfaceException e) {
            // Then
            assertThat(e.getResponse().getStatus()).isEqualTo(404);
        }
        
    }
}
