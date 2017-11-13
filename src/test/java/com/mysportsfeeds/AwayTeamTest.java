package com.mysportsfeeds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AwayTeamTest {

    private AwayTeam awayTeam;

    @Before
    public void setUp() throws Exception {
        awayTeam = new AwayTeam();
    }

    @Test
    public void setAndGetAbbreviationTest() throws Exception {
        awayTeam.setAbbreviation("GBP");

        assertEquals(awayTeam.getAbbreviation(), "GBP");
    }

    @Test
    public void setAndGetIDTest() throws Exception {
        awayTeam.setID("12345");

        assertEquals(awayTeam.getID(), "12345");
    }

    @Test
    public void setAndGetCityTest() throws Exception {
        awayTeam.setCity("Green Bay");

        assertEquals(awayTeam.getCity(), "Green Bay");
    }

    @Test
    public void setAndGetNameTest() throws Exception {
        awayTeam.setName("Packers");

        assertEquals(awayTeam.getName(), "Packers");
    }

    @Test
    public void toStringTest() throws Exception {
        awayTeam.setAbbreviation("GBP");
        awayTeam.setID("12345");
        awayTeam.setCity("Green Bay");
        awayTeam.setName("Packers");

        assertEquals(awayTeam.toString(), "AwayTeam{abbreviation = 'GBP',iD = '12345',city = 'Green Bay',name = 'Packers'}");
    }

}