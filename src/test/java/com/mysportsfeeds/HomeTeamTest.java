package com.mysportsfeeds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeTeamTest {

    private HomeTeam homeTeam;

    @Before
    public void setUp() throws Exception {
        homeTeam = new HomeTeam();
    }

    @Test
    public void setAndGetAbbreviationTest() throws Exception {
        homeTeam.setAbbreviation("GBP");

        assertEquals(homeTeam.getAbbreviation(), "GBP");
    }

    @Test
    public void setAndGetIDTest() throws Exception {
        homeTeam.setID("12345");

        assertEquals(homeTeam.getID(), "12345");
    }

    @Test
    public void setAndGetCityTest() throws Exception {
        homeTeam.setCity("Green Bay");

        assertEquals(homeTeam.getCity(), "Green Bay");
    }

    @Test
    public void setAndGetNameTest() throws Exception {
        homeTeam.setName("Packers");

        assertEquals(homeTeam.getName(), "Packers");
    }

    @Test
    public void toStringTest() throws Exception {
        homeTeam.setName("Packers");
        homeTeam.setCity("Green Bay");
        homeTeam.setID("12345");
        homeTeam.setAbbreviation("GBP");

        assertEquals(homeTeam.toString(), "HomeTeam{abbreviation = 'GBP',iD = '12345',city = 'Green Bay',name = 'Packers'}");
    }

}