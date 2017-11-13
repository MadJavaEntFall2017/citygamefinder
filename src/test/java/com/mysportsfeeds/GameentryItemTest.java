package com.mysportsfeeds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameentryItemTest {

    private GameentryItem gameentryItem;
    private Object originalTime;
    private AwayTeam awayTeam;
    private Object originalDate;
    private Object delayReason;
    private HomeTeam homeTeam;

    @Before
    public void setUp() {
        gameentryItem = new GameentryItem();
        originalTime = new Object();
        originalTime = "16:00:00.00.00.00.000000";
        awayTeam = new AwayTeam();
        awayTeam.setName("Packers");
        originalDate = "2017-11-01";
        delayReason = "Weather";
        homeTeam = new HomeTeam();
        homeTeam.setName("Lions");
    }

    @Test
    public void setAndGetDateTest() throws Exception {
        gameentryItem.setDate("2017-11-01");

        assertEquals(gameentryItem.getDate(), "2017-11-01");
    }

    @Test
    public void setAndGetOriginalTimeTest() throws Exception {
        gameentryItem.setOriginalTime(originalTime);

        assertEquals(gameentryItem.getOriginalTime().toString(), "16:00:00.00.00.00.000000");
    }

    @Test
    public void setAndGetAwayTeamTest() throws Exception {
        gameentryItem.setAwayTeam(awayTeam);

        assertEquals(gameentryItem.getAwayTeam().toString(), "AwayTeam{abbreviation = 'null',iD = 'null',city = 'null',name = 'Packers'}");
    }

    @Test
    public void setAndGetOriginalDateTest() throws Exception {
        gameentryItem.setOriginalDate(originalDate);

        assertEquals(gameentryItem.getOriginalDate().toString(), "2017-11-01");
    }

    @Test
    public void setAndGetDelayedOrPostponedReasonTest() throws Exception {
        gameentryItem.setDelayedOrPostponedReason(delayReason);

        assertEquals(gameentryItem.getDelayedOrPostponedReason().toString(), "Weather");
    }

    @Test
    public void setAndGetScheduleStatusTest() throws Exception {
        gameentryItem.setScheduleStatus("Current");

        assertEquals(gameentryItem.getScheduleStatus(), "Current");
    }

    @Test
    public void setAndGetHomeTeamTest() throws Exception {
        gameentryItem.setHomeTeam(homeTeam);

        assertEquals(gameentryItem.getHomeTeam().toString(), "HomeTeam{abbreviation = 'null',iD = 'null',city = 'null',name = 'Lions'}");
    }

    @Test
    public void setAndGetLocationTest() throws Exception {
        gameentryItem.setLocation("Detroit");

        assertEquals(gameentryItem.getLocation(), "Detroit");
    }

    @Test
    public void setAndGetIdTest() throws Exception {
        gameentryItem.setId("12345");

        assertEquals(gameentryItem.getId(), "12345");
    }

    @Test
    public void setAndGetTimeTest() throws Exception {
        gameentryItem.setTime("16:00:00.00.00.00.000000");

        assertEquals(gameentryItem.getTime(), "16:00:00.00.00.00.000000");
    }

    @Test
    public void setAndGetZipCodeTest() throws Exception {
        gameentryItem.setZipCode("98765");

        assertEquals(gameentryItem.getZipCode(), "98765");
    }

    @Test
    public void toStringTest() throws Exception {
        gameentryItem.setZipCode("98765");
        gameentryItem.setTime("16:00:00.00.00.00.000000");
        gameentryItem.setId("12345");
        gameentryItem.setLocation("Detroit");
        gameentryItem.setHomeTeam(homeTeam);
        gameentryItem.setScheduleStatus("Current");
        gameentryItem.setDelayedOrPostponedReason(delayReason);
        gameentryItem.setOriginalDate(originalDate);
        gameentryItem.setAwayTeam(awayTeam);
        gameentryItem.setOriginalTime(originalTime);
        gameentryItem.setDate("2017-11-01");

        assertEquals(gameentryItem.toString(), "GameentryItem{date = '2017-11-01',originalTime = '16:00:00.00.00.00.000000',awayTeam = 'AwayTeam{abbreviation = 'null',iD = 'null',city = 'null',name = 'Packers'}',originalDate = '2017-11-01',delayedOrPostponedReason = 'Weather',scheduleStatus = 'Current',homeTeam = 'HomeTeam{abbreviation = 'null',iD = 'null',city = 'null',name = 'Lions'}',location = 'Detroit',id = '12345',time = '16:00:00.00.00.00.000000'}");
    }

}