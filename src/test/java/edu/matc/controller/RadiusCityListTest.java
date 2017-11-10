package edu.matc.controller;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class RadiusCityListTest {

    private RadiusCityList radiusCityList;

    @Before
    public void testSetup() {
        radiusCityList = new RadiusCityList();
    }

    @Test
    public void setZipCodeTest() throws Exception {
        radiusCityList.setZipCode("12345");

        assertEquals(radiusCityList.getZipCode(), "12345");
    }

    @Test
    public void setMileRadiusTest() throws Exception {
        radiusCityList.setMileRadius("50");

        assertEquals(radiusCityList.getMileRadius(), "50");
    }


    @Test
    public void findRadiusCities() throws Exception {
        radiusCityList = new RadiusCityList("53718", "200");

        HashSet<String> radiusCities = radiusCityList.findRadiusCities();

        assertEquals(radiusCities.size(), 2525);
    }

}