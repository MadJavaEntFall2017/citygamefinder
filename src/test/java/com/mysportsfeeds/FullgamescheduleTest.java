package com.mysportsfeeds;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FullgamescheduleTest {

    private Fullgameschedule fullgameschedule;
    private List<GameentryItem> gameentryItems;

    @Before
    public void setUp() throws Exception {
        fullgameschedule = new Fullgameschedule();
        gameentryItems = new ArrayList<GameentryItem>();

        GameentryItem gameentryItem1 = new GameentryItem();
        GameentryItem gameentryItem2 = new GameentryItem();

        gameentryItem1.setZipCode("12345");
        gameentryItem2.setZipCode("09876");

        gameentryItems.add(gameentryItem1);
        gameentryItems.add(gameentryItem2);
    }

    @Test
    public void setAndGetLastUpdatedOnTest() throws Exception {
        fullgameschedule.setLastUpdatedOn("2017-11-01");

        assertEquals(fullgameschedule.getLastUpdatedOn(), "2017-11-01");
    }

    @Test
    public void setAndGetGameentryTest() throws Exception {
        fullgameschedule.setGameentry(gameentryItems);

        assertEquals(fullgameschedule.getGameentry().size(), 2);
    }

    @Test
    public void toStringTest() throws Exception {
        fullgameschedule.setLastUpdatedOn("2017-11-01");
        fullgameschedule.setGameentry(gameentryItems);

        assertEquals(fullgameschedule.toString(), "Fullgameschedule{lastUpdatedOn = '2017-11-01',gameentry = '[GameentryItem{date = 'null',originalTime = 'null',awayTeam = 'null',originalDate = 'null',delayedOrPostponedReason = 'null',scheduleStatus = 'null',homeTeam = 'null',location = 'null',id = 'null',time = 'null'}, GameentryItem{date = 'null',originalTime = 'null',awayTeam = 'null',originalDate = 'null',delayedOrPostponedReason = 'null',scheduleStatus = 'null',homeTeam = 'null',location = 'null',id = 'null',time = 'null'}]'}");
    }

}