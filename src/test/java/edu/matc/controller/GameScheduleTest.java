package edu.matc.controller;

import com.mysportsfeeds.Fullgameschedule;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import edu.matc.entity.Stadiums;
import edu.matc.persistence.StadiumsDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameScheduleTest {

    private GameSchedule gameSchedule;

    @Before
    public void testSetup() {
        gameSchedule = new GameSchedule("NFL");
    }

    @Test
    public void getScheduleTest() throws Exception {

        List<GameentryItem> gameentryItems = gameSchedule.getSchedule();

        assertEquals(gameentryItems.size(), 256);
        //assertTrue(gameentryItems.size() > 0);
    }

    @Test
    public void gameApiCallTest() throws Exception {
        GameResponse gameResponse = gameSchedule.gameApiCall();

        assertTrue(gameResponse.getFullgameschedule() != null);
    }

    @Test
    public void updateZipTest() throws Exception {
        GameResponse gameResponse = gameSchedule.gameApiCall();

        List<GameentryItem> gameentryItems = gameSchedule.updateZip(gameResponse);

        assertEquals(gameentryItems.size(), 256);
    }

    @Test
    public void findStadiumZipTest() throws Exception {
        StadiumsDao stadiumsDao = new StadiumsDao();

        List<Stadiums> allStadiums = stadiumsDao.getAllStadiums();

        String zipCodes = gameSchedule.findStadiumZip("Lambeau Field", allStadiums);

        assertEquals(zipCodes, "54304");
    }

}