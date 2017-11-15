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

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getMessage1Test() throws Exception {
        Response response = sportsService.getMessage("NFL");

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getMessage1BadRequestTest() throws Exception {
        Response response = sportsService.getMessage("ABA");
        int status = response.getStatus();

        assertEquals(400, response.getStatus());
    }

    @Test
    public void getMessage2Test() throws Exception {
        Response response = sportsService.getMessage("53718", "200");

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getMessage3Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200");

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getMessage4Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200", "2017-12-01");

        assertEquals(200, response.getStatus());
    }

    @Test
    public void getMessage5Test() throws Exception {
        Response response = sportsService.getMessage("NFL", "53718", "200", "2017-12-01", "2017-12-31");

        assertEquals(200, response.getStatus());
    }

    @Test
    public void validateSportParamTest() throws Exception {

        assertTrue(sportsService.validateSportParam("NFL"));
    }

    @Test
    public void errorResponseTest() throws Exception {

        Response response = sportsService.errorResponse(400, "test exception", "testurl.edu");

        assertEquals(400, response.getStatus());
    }

    /**@Test
    public void zipCodeApiErrorResponseTest() throws Exception {
        RadiusCityList zipList = new RadiusCityList("53718", "50");

        Response.StatusType statusType;
        statusType.
        Response response = sportsService.zipCodeApiErrorResponse(zipList.getStatusInfo(), "00000", "50" );

        assertEquals(000, response.getStatus());
    }

    @Test
    public void mysportsfeedsApiErrorResponseTest() throws Exception {
        Response response = sportsService.mysportsfeedsApiErrorResponse(503);

        assertEquals(response.getStatus(), 500);
    } */

    @Test
    public void validDateTest() throws Exception {
        assertTrue(sportsService.validDate("2017-11-01"));
    }

}