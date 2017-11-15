package com.citygamefinder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SportsItemTest {

    private SportsItem sportsItem;

    @Before
    public void testSetup() {
        sportsItem = new SportsItem();
    }

    @Test
    public void setAndGetSportTest() throws Exception {
        sportsItem.setSport("NFL");

        assertEquals(sportsItem.getSport(), "NFL");
    }

    @Test
    public void toStringTest() throws Exception {
        sportsItem.setSport("NFL");

        assertEquals(sportsItem.toString(), "SportsItem{sport = 'NFL'}");
    }

}