package edu.matc.controller;

import com.citygamefinder.SportsItem;
import com.citygamefinder.SportsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.Fullgameschedule;
import com.mysportsfeeds.GameentryItem;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The SportsService class handles all combinations of calls to api and does the processing necessary.
 *
 * @author Great Lakes Team
 */
@Path("/sports")
public class SportsService {

    private final Logger log = Logger.getLogger(this.getClass());
    private static final String NFL = "NFL";
    private static final String NHL = "NHL";
    private static final String NBA = "NBA";
    private static final String MORE_INFO_URL = "https://github.com/MadJavaEntFall2017/citygamefinder";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * This method will return the available sports for our service
     *
     * @return the Response the json response that shows what sports we allow.
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() throws Exception {

        List <SportsItem> sportsList = new ArrayList<SportsItem>();

        SportsItem nfl = new SportsItem();
        SportsItem nba = new SportsItem();
        SportsItem nhl = new SportsItem();

        nfl.setSport(NFL);
        nba.setSport(NBA);
        nhl.setSport(NHL);

        sportsList.add(nfl);
        sportsList.add(nba);
        sportsList.add(nhl);

        SportsResponse response = new SportsResponse();
        response.setSports(sportsList);

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        return Response.status(200).entity(output).build();
    }

    /**
     * This method will call the mysportsfeeds api to return all games for a passed in sport
     *
     * @param sport the sport to use to find the schedules
     * @return the Response from the api processing
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}")
    public Response getMessage(@PathParam("sport") String sport)  throws Exception {

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> returnGames = null;

        try {
            returnGames = schedule.getSchedule();
        } catch (HibernateException hibernateException) {
            log.info("hibernateException", hibernateException);
            return errorResponse(503, "Error connecting to database for nfl", MORE_INFO_URL);
        } catch (Exception e) {
            log.info("exception", e);
            return errorResponse(400, "Error connecting to database for nfl", MORE_INFO_URL);
        }

        if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }


    /**
     * The method will call the mysportsfeeds api to return all sports games with a given radius
     *
     * @param zipCode the zip code to use as the start point
     * @param radius the radius to search within
     * @return the Response from the api processing
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{zip}/{radius}")
    public Response getMessage(@PathParam("zip") String zipCode,
                               @PathParam("radius") String radius)  throws Exception{

        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        if (zipCities == null) {
            return zipCodeApiErrorResponse(zipList.getStatusInfo(), zipCode, radius);
        }

        GameSchedule nflSchedule = new GameSchedule(NFL);
        GameSchedule nbaSchedule = new GameSchedule(NBA);
        GameSchedule nhlSchedule = new GameSchedule(NHL);

        List<GameentryItem> games = new ArrayList<GameentryItem>();

        //games.addAll(nflSchedule.getSchedule());
        try {
            games.addAll(nflSchedule.getSchedule());
        } catch (Exception e) {
            log.info("exception", e);
            return errorResponse(400, "Error connecting to database for nfl", MORE_INFO_URL);
        }
        games.addAll(nbaSchedule.getSchedule());
        games.addAll(nhlSchedule.getSchedule());

        log.info("nflSchedule.getResponseCode() " + nflSchedule.getResponseCode());
        log.info("nbaSchedule.getResponseCode() " + nbaSchedule.getResponseCode());
        log.info("nhlSchedule.getResponseCode() " + nhlSchedule.getResponseCode());

        //if all failed, then return error
        if (nflSchedule.getResponseCode() != 200
                && nbaSchedule.getResponseCode() != 200
                && nhlSchedule.getResponseCode() != 200) {
            int[] responseCodes = {nflSchedule.getResponseCode(), nbaSchedule.getResponseCode(), nhlSchedule.getResponseCode()};
            return mysportsfeedsApiErrorResponse(Arrays.stream(responseCodes).max().getAsInt());
        }

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            if (zipCities.contains(currentGame.getZipCode())) {
                returnGames.add(currentGame);
            }
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }

    /**
     * The method will call the mysportsfeeds api to return all games for a passed in sport with a given radius
     *
     * @param sport the sport to search for
     * @param zipCode the zip code to use as the start point
     * @param radius the radius to search within
     * @return the Response from the api processing
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius)  throws Exception{

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        if (zipCities == null) {
            return zipCodeApiErrorResponse(zipList.getStatusInfo(), zipCode, radius);
        }

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

        if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            if (zipCities.contains(currentGame.getZipCode())) {
                returnGames.add(currentGame);
            }
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }


    /**
     * The method will call the mysportsfeeds api to return all games for a passed in sport with a given radius with a
     * given start date
     *
     * @param sport the sport to search for
     * @param zipCode the zip code to use as the start point
     * @param radius the radius to search within
     * @param fromDate the begin date to search for sports
     * @return the Response from the api processing
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}/{fromDate}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius,
                               @PathParam("fromDate") String fromDate)  throws Exception{

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        if (!validDate(fromDate)) {
            String returnMessage = "Bad Request! From Date " + fromDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        if (zipCities == null) {
            return zipCodeApiErrorResponse(zipList.getStatusInfo(), zipCode, radius);
        }

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

        if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }

        LocalDate gameLocalDate;
        LocalDate fromLocalDate = LocalDate.parse(fromDate, DATE_TIME_FORMATTER);

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            gameLocalDate = LocalDate.parse(currentGame.getDate(), DATE_TIME_FORMATTER);

            if (zipCities.contains(currentGame.getZipCode())
                    && (gameLocalDate.isEqual(fromLocalDate) || gameLocalDate.isAfter(fromLocalDate))) {
                returnGames.add(currentGame);
            }
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }

    /**
     * The method will call the mysportsfeeds api to return all games between passed in dates for a given sport within
     * a given radius
     *
     * @param sport the sport to search for
     * @param zipCode the zip code to use as the start point
     * @param radius the radius to search within
     * @param fromDate the begin date to search for sports
     * @param toDate the end date to search for sports
     * @return the Response from the api processing
     * @throws Exception if there is a general exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}/{fromDate}/{toDate}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius,
                               @PathParam("fromDate") String fromDate,
                               @PathParam("toDate") String toDate)  throws Exception{

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        if (!validDate(fromDate)) {
            String returnMessage = "Bad Request! From Date " + fromDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        if (!validDate(toDate)) {
            String returnMessage = "Bad Request! From Date " + toDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, MORE_INFO_URL);
        }

        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        if (zipCities == null) {
            return zipCodeApiErrorResponse(zipList.getStatusInfo(), zipCode, radius);
        }

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

        if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }

        LocalDate gameLocalDate;
        LocalDate fromLocalDate = LocalDate.parse(fromDate, DATE_TIME_FORMATTER);
        LocalDate toLocalDate = LocalDate.parse(toDate, DATE_TIME_FORMATTER);

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            gameLocalDate = LocalDate.parse(currentGame.getDate(), DATE_TIME_FORMATTER);

            if (zipCities.contains(currentGame.getZipCode())
                    && (gameLocalDate.isEqual(fromLocalDate) || gameLocalDate.isAfter(fromLocalDate))
                    && (gameLocalDate.isEqual(toLocalDate) || gameLocalDate.isBefore(toLocalDate))) {
                returnGames.add(currentGame);
            }
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }

    /**
     * Validates the current sports that we allow our service to use
     *
     * @param sport the sport being used in our api service
     * @return if the sport being used is the one we use, then true; else false
     */
    public boolean validateSportParam (String sport) {
        switch (sport.toUpperCase()) {
            case NFL:
            case NHL:
            case NBA:
                return true;
            default:
                return false;
        }
    }

    /**
     * Handles the error response from our service
     *
     * @param status the status code of the response
     * @param returnMessage the message of the response
     * @param moreInfoUrl the url to check for more info
     * @return the error response
     * @throws Exception generic exception handling
     */
    public Response errorResponse(int status, String returnMessage, String moreInfoUrl) throws Exception {
        JSONObject errorJSON = new JSONObject();

        errorJSON.put("status", status);
        errorJSON.put("errorMessage", returnMessage);
        errorJSON.put("moreInfoUrl", moreInfoUrl);

        return Response.status(400).entity(errorJSON.toString()).build();
    }

    /**
     * Handles errors received from calling the zip code api
     *
     * @param statusType the response status type
     * @param zipCode the zip code used to call the api
     * @param radius the radius used to call the api
     * @return the response with the error messages from our service
     * @throws Exception Generic exception
     */
    public Response zipCodeApiErrorResponse(Response.StatusType statusType, String zipCode, String radius) throws Exception {
        int statusCode;
        String returnMessage;

        switch (statusType.getFamily()) {
            case SERVER_ERROR:
                statusCode = 500;
                returnMessage = "Zip Code API is currently down; please try again later";
            case CLIENT_ERROR:
                if (statusType.getStatusCode() == 400) {
                    statusCode = statusType.getStatusCode();
                    returnMessage = "Bad call to Zip Code API using zip code " + zipCode + " and radius " + radius;
                }
            default:
                statusCode = 400;
                returnMessage = "Error encountered calling Zip Code API, returned with staus " + statusType.getStatusCode();
        }

        return errorResponse(statusCode, returnMessage, MORE_INFO_URL);
    }

    /**
     * Handles the error processing for the mysportfeeds api
     *
     * @param responseCode the response code from the api
     * @return the response from our api
     * @throws Exception Generic exception
     */
    public Response mysportsfeedsApiErrorResponse(int responseCode) throws Exception {
        int statusCode;
        String returnMessage;

        switch (responseCode) {
            case 500:
            case 503:
                statusCode = 500;
                returnMessage = "mysportsfeed API is currently down; please try again later";
            case 400:
                statusCode = 400;
                returnMessage = "Bad call to mysportsfeed API";
            default:
                statusCode = 400;
                returnMessage = "Error encountered calling mysportsfeeds API, returned with staus " + statusCode;
        }

        return errorResponse(statusCode, returnMessage, MORE_INFO_URL);
    }

    /**
     * This checks to make sure the date passed in in a valid format
     *
     * @param dateString the date passed to the api
     * @return if the date is valid, then true; else false
     */
    public boolean validDate(String dateString) {
        try{
            LocalDate localDate = LocalDate.parse(dateString, DATE_TIME_FORMATTER);
        } catch (Exception exception) {
            log.error("Input string date of " + dateString + " is not a valid date.", exception);
            return false;
        }
        return true;
    }
}

