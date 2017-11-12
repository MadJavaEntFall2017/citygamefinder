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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() throws Exception {

        List <SportsItem> sportsList = new ArrayList<SportsItem>();

        SportsItem nfl = new SportsItem();
        SportsItem nba = new SportsItem();
        SportsItem nhl = new SportsItem();

        nfl.setSport("NFL");
        nba.setSport("NBA");
        nhl.setSport("NHL");

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
            int statusCode;
            //do we want to evaluate zip code api response status and send different error response?
            String returnMessage = "Error encountered with Zip Code API";
            switch (zipList.getStatusInfo().getFamily()) {
                case SERVER_ERROR:
                    statusCode = 503;
                    returnMessage = "Zip Code API is currently down; please try again later";
                case CLIENT_ERROR:
                    if (zipList.getStatusInfo().getStatusCode() == 400) {
                        statusCode = zipList.getStatusInfo().getStatusCode();
                        returnMessage = "Bad call to Zip Code API using zip code " + zipCode + " and radius " + radius;
                    }
                default:
                    statusCode = 400;
                    returnMessage = "Error encountered calling Zip Code API, returned with staus " + zipList.getStatusInfo().getStatusCode();
            }

            return errorResponse(statusCode, returnMessage, "https://github.com/MadJavaEntFall2017/citygamefinder");
        }

        GameSchedule nflSchedule = new GameSchedule("NFL");
        GameSchedule nbaSchedule = new GameSchedule("NBA");
        GameSchedule nhlSchedule = new GameSchedule("NHL");
        GameSchedule mlbSchedule = new GameSchedule("MLB");

        List<GameentryItem> games = new ArrayList<GameentryItem>();

        games.addAll(nflSchedule.getSchedule());
        games.addAll(nbaSchedule.getSchedule());
        games.addAll(nhlSchedule.getSchedule());
        games.addAll(mlbSchedule.getSchedule());

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

        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

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


        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

        LocalDate gameLocalDate;
        LocalDate fromLocalDate = LocalDate.parse(fromDate, formatter);

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            gameLocalDate = LocalDate.parse(currentGame.getDate(), formatter);

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


        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();

        GameSchedule schedule = new GameSchedule(sport);
        List<GameentryItem> games = schedule.getSchedule();

        LocalDate gameLocalDate;
        LocalDate fromLocalDate = LocalDate.parse(fromDate, formatter);
        LocalDate toLocalDate = LocalDate.parse(toDate, formatter);

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            gameLocalDate = LocalDate.parse(currentGame.getDate(), formatter);

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
        Boolean validSport = false;

        switch (sport.toUpperCase()) {
            case "NFL":
            case "NHL":
            case "NBA":
                validSport = true;
                break;
            default:
                validSport = false;
        }

        return validSport;
    }

    private Response errorResponse(int status, String returnMessage, String moreInfoUrl) throws Exception {
        JSONObject errorJSON = new JSONObject();

        errorJSON.put("status", status);
        errorJSON.put("errorMessage", returnMessage);
        errorJSON.put("moreInfoUrl", moreInfoUrl);

        return Response.status(400).entity(errorJSON.toString()).build();
    }
}

