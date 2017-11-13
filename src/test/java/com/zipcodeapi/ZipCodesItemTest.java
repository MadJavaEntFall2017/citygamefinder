package com.zipcodeapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZipCodesItemTest {

    private ZipCodesItem zipCodesItem;

    @Before
    public void setUp() throws Exception {
        zipCodesItem = new ZipCodesItem();
    }

    @Test
    public void setAndGetDistanceTest() throws Exception {
        zipCodesItem.setDistance(50);

        assertEquals(zipCodesItem.getDistance(), 50);
    }

    @Test
    public void setAndGetCityTest() throws Exception {
        zipCodesItem.setCity("Madison");

        assertEquals(zipCodesItem.getCity(), "Madison");
    }

    @Test
    public void setAndGetStateTest() throws Exception {
        zipCodesItem.setState("WI");

        assertEquals(zipCodesItem.getState(), "WI");
    }

    @Test
    public void setAndGetZipCodeTest() throws Exception {
        zipCodesItem.setZipCode("53718");

        assertEquals(zipCodesItem.getZipCode(), "53718");
    }

    @Test
    public void toStringTest() throws Exception {
        zipCodesItem.setZipCode("53718");
        zipCodesItem.setState("WI");
        zipCodesItem.setCity("Madison");
        zipCodesItem.setDistance(50);

        assertEquals(zipCodesItem.toString(), "ZipCodesItem{distance = '50',city = 'Madison',state = 'WI',zip_code = '53718'}");
    }

}