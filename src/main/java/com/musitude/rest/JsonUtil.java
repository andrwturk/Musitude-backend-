package com.musitude.rest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Author: Iurii Lytvynenko
 */
public class JsonUtil {
    private JsonUtil() {
    }

    public static <T> T read(String source, Class<T> clazz) {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        try {
            return mapper.readValue(source, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
