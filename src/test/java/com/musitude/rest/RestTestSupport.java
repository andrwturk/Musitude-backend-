package com.musitude.rest;

import com.musitude.test_data.DataGenerator;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Author: Iurii Lytvynenko
 */
public class RestTestSupport {

    /**
     * Jersey test instance. It is actually a JUnit test, that's why
     * a composition is used instead of inheritance.
     */
    private JerseyTest jerseyTest;

    @BeforeClass
    public void setUp() throws Exception {
        new DataGenerator().generateData();
        ClientConfig cc = new DefaultClientConfig();
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        WebAppDescriptor webAppDescriptor =
                new WebAppDescriptor.Builder("com.musitude.rest") // NON-NLS
                        .initParam(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE.toString())
                        .clientConfig(cc)
                        .build();
        this.jerseyTest = new JerseyTest(webAppDescriptor) {};
        this.jerseyTest.setUp();
    }                                       

    @AfterClass
    public void tearDown() throws Exception {
        this.jerseyTest.tearDown();
    }
    
    public WebResource resource() {
        return jerseyTest.resource();
    }
}
