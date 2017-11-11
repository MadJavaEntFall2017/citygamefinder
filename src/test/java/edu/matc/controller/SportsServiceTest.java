package edu.matc.controller;

import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class SportsServiceTest {

    private SportsService sportsService;

    @Before
    public void testSetup() {
        sportsService = new SportsService();
    }

    @Test
    public void getMessageTest() throws Exception {
        Response response = sportsService.getMessage();

        assertEquals(true, response.hasEntity());
    }

    @Test
    public void getMessage1Test() throws Exception {
        Response response = sportsService.getMessage("NFL");

        assertEquals(true, response.hasEntity());
    }

    @Test
    public void getMessage2Test() throws Exception {
        Response response = sportsService.getMessage("53718", "200");

        assertEquals(true, response.hasEntity());
    }

    @Test
    public void getMessage3Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200");

        assertEquals(true, response.hasEntity());
    }

    @Test
    public void getMessage4Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200", "2017-12-01");

        assertEquals(true, response.hasEntity());
    }

    @Test
    public void getMessage5Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200", "2017-12-01", "2017-12-31");

        assertEquals(true, response.hasEntity());
    }

}