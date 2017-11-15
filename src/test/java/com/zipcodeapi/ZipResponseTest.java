package com.zipcodeapi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZipResponseTest {

    private ZipResponse zipResponse;
    private List<ZipCodesItem> zipCodesItems;

    @Before
    public void setUp() throws Exception {
        zipResponse = new ZipResponse();
        zipCodesItems = new ArrayList<ZipCodesItem>();

        ZipCodesItem zipCodesItem1 = new ZipCodesItem();
        ZipCodesItem zipCodesItem2 = new ZipCodesItem();

        zipCodesItem1.setDistance(10);
        zipCodesItem1.setCity("Madison");
        zipCodesItem1.setState("WI");
        zipCodesItem1.setZipCode("12345");

        zipCodesItem2.setDistance(50);
        zipCodesItem2.setCity("Detroit");
        zipCodesItem2.setState("MI");
        zipCodesItem2.setZipCode("98765");

        zipCodesItems.add(zipCodesItem1);
        zipCodesItems.add(zipCodesItem2);
    }

    @Test
    public void setAndGetZipCodesTest() throws Exception {
        zipResponse.setZipCodes(zipCodesItems);

        assertEquals(zipResponse.getZipCodes().size(), 2);
    }

    @Test
    public void toStringTest() throws Exception {
        zipResponse.setZipCodes(zipCodesItems);

        assertEquals(zipResponse.toString(), "ZipResponse{zip_codes = '[ZipCodesItem{distance = '10',city = 'Madison',state = 'WI',zip_code = '12345'}, ZipCodesItem{distance = '50',city = 'Detroit',state = 'MI',zip_code = '98765'}]'}");
    }

}