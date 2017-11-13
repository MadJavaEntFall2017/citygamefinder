package com.citygamefinder;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SportsResponseTest {

    private SportsResponse sportsResponse;
    List<SportsItem> sportsItems;

    @Before
    public void setUp() throws Exception {
        sportsResponse = new SportsResponse();
        sportsItems = new ArrayList<SportsItem>();

        SportsItem sportsItem1 = new SportsItem();
        SportsItem sportsItem2 = new SportsItem();
        SportsItem sportsItem3 = new SportsItem();
        SportsItem sportsItem4 = new SportsItem();

        sportsItem1.setSport("NFL");
        sportsItem2.setSport("NBA");
        sportsItem3.setSport("NHL");
        sportsItem4.setSport("MLB");

        sportsItems.add(sportsItem1);
        sportsItems.add(sportsItem2);
        sportsItems.add(sportsItem3);
        sportsItems.add(sportsItem4);
    }

    @Test
    public void setAndGetSportsTest() throws Exception {

        sportsResponse.setSports(sportsItems);

        assertEquals(sportsResponse.getSports().size(), 4);
    }

    @Test
    public void toStringTest() throws Exception {
        sportsResponse.setSports(sportsItems);

        assertEquals(sportsResponse.toString(), "SportsResponse{sports = '[SportsItem{sport = 'NFL'}, SportsItem{sport = 'NBA'}, SportsItem{sport = 'NHL'}, SportsItem{sport = 'MLB'}]'}");
    }

}