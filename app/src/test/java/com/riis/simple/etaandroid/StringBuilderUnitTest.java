package com.riis.simple.etaandroid;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringBuilderUnitTest {

    private UrlStringBuilder urlStringBuilder;

    public StringBuilderUnitTest() {
        urlStringBuilder = new UrlStringBuilder();
    }

    @Test
    public void testGetStopsUrl() throws Exception {
        assertEquals(urlStringBuilder.getStopsUrl(1, "1", "northbound", "weekday"), "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/1/routes/1/northbound/weekday/1/stops");
    }

    @Test
    public void testGetRoutesUrl() throws Exception {
        assertEquals(urlStringBuilder.getRoutesUrl(1), "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/1/routes");
    }
}