package com.mysportsfeeds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameResponseTest {

    private GameResponse gameResponse;
    private Fullgameschedule fullgameschedule;

    @Before
    public void setUp() throws Exception {
        gameResponse = new GameResponse();
        fullgameschedule = new Fullgameschedule();
        fullgameschedule.setLastUpdatedOn("2017-11-01");
    }

    @Test
    public void setAndGetFullgamescheduleTest() throws Exception {
        gameResponse.setFullgameschedule(fullgameschedule);

        assertEquals(gameResponse.getFullgameschedule().toString(), "Fullgameschedule{lastUpdatedOn = '2017-11-01',gameentry = 'null'}");
    }

    @Test
    public void toStringTest() throws Exception {
        gameResponse.setFullgameschedule(fullgameschedule);

        assertEquals(gameResponse.toString(), "GameResponse{fullgameschedule = 'Fullgameschedule{lastUpdatedOn = '2017-11-01',gameentry = 'null'}'}");
    }

}