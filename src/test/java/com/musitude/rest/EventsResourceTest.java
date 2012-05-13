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
public class EventsResourceTest extends RestTestSupport {

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

    @Test
    public void testCheckIn() throws Exception {
        // Given
        WebResource eventPath = resource().path("event").path("1");
        WebResource path = eventPath.path("some_device");
        
        // when
        path.post();
        
        // then
        assertThat(eventPath.get(String.class)).contains("guests\":1");
    }
    
    @Test
    public void testLike() throws Exception {
        // Given
        WebResource eventPath = resource().path("event").path("1");
        WebResource path = eventPath.path("some_device_likes");
        
        // when
        path.post();
        path.path("!").put();
        
        // then
        assertThat(eventPath.get(String.class)).contains("likes\":1");
    }
}
