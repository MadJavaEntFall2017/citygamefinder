package edu.matc.controller;

import com.citygamefinder.SportsItem;
import com.citygamefinder.SportsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.Fullgameschedule;
import com.mysportsfeeds.GameentryItem;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Path("/sports")
public class SportsService {

    private final Logger log = Logger.getLogger(this.getClass());
    private static final String NFL = "NFL";
    private static final String NHL = "NHL";
    private static final String NBA = "NBA";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}")
    public Response getMessage(@PathParam("sport") String sport)  throws Exception {

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
        }

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> returnGames = schedule.getSchedule();

        if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }

        Fullgameschedule returnSchedule = new Fullgameschedule();
        returnSchedule.setGameentry(returnGames);
        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnSchedule);
        return Response.status(200).entity(output).build();
    }

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
        //GameSchedule mlbSchedule = new GameSchedule("MLB");

        //todo: should we check all of the calls? if all are bad then bad, is one is good, then move forward?? Maybe only add the good ones?
        /*if (schedule.getResponseCode() != 200) {
            return mysportsfeedsApiErrorResponse(schedule.getResponseCode());
        }*/

        List<GameentryItem> games = new ArrayList<GameentryItem>();

        games.addAll(nflSchedule.getSchedule());
        games.addAll(nbaSchedule.getSchedule());
        games.addAll(nhlSchedule.getSchedule());
        //games.addAll(mlbSchedule.getSchedule());

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius)  throws Exception{

        if (!validateSportParam(sport)) {
            String returnMessage = "Bad Request! Request for sport " + sport
                    + " is not supported. Request a sport that is in the supported list";

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
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

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
        }

        if (!validDate(fromDate)) {
            String returnMessage = "Bad Request! From Date " + fromDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
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

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
        }

        if (!validDate(fromDate)) {
            String returnMessage = "Bad Request! From Date " + fromDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
        }

        if (!validDate(toDate)) {
            String returnMessage = "Bad Request! From Date " + toDate
                    + " is not a valid date. Please provide a valid date in yyyy-mm-dd format";

            return errorResponse(400, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
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


    private boolean validateSportParam (String sport) {
        switch (sport.toUpperCase()) {
            case NFL:
            case NHL:
            case NBA:
                return true;
            default:
                return false;
        }
    }

    private Response errorResponse(int status, String returnMessage, String moreInfoUrl) throws Exception {
        JSONObject errorJSON = new JSONObject();

        errorJSON.put("status", status);
        errorJSON.put("errorMessage", returnMessage);
        errorJSON.put("moreInfoUrl", moreInfoUrl);

        return Response.status(400).entity(errorJSON.toString()).build();
    }


    private Response zipCodeApiErrorResponse(Response.StatusType statusType, String zipCode, String radius) throws Exception {
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

        return errorResponse(statusCode, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
    }

    private Response mysportsfeedsApiErrorResponse(int responseCode) throws Exception {
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

        return errorResponse(statusCode, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
    }

    private boolean validDate(String dateString) {
        try{
            LocalDate localDate = LocalDate.parse(dateString, DATE_TIME_FORMATTER);
        } catch (Exception exception) {
            log.error("Input string date of " + dateString + " is not a valid date.", exception);
            return false;
        }
        return true;
    }
}

