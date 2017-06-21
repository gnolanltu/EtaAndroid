package com.riis.simple.etaandroid;

import com.riis.simple.etaandroid.model.UrlStringBuilder;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringBuilderUnitTest {

    @Test
    public void testGetStopsUrl() throws Exception {
        assertEquals(UrlStringBuilder.INSTANCE.getStopsUrl(1, "1", "northbound", "weekday"),
                "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/1/routes/1/northbound/weekday/1/stops");
    }

    @Test
    public void testGetRoutesUrl() throws Exception {
        assertEquals(UrlStringBuilder.INSTANCE.getRoutesUrl(1),
                "http://ec2-204-236-211-33.compute-1.amazonaws.com:8080/companies/1/routes");
    }
}