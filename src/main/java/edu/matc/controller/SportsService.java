package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameentryItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Path("/sports")
public class SportsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() throws Exception {

        String outputString = "You want to know what sports we support";

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputString);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}")
    public Response getMessage(@PathParam("sport") String sport)  throws Exception{

        String outputString = "You want the full schedule for " + sport;

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputString);
        return Response.status(200).entity(output).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{zip}/{radius}")
    public Response getMessage(@PathParam("zip") String zipCode,
                               @PathParam("radius") String radius)  throws Exception{

        String outputString = "You want the games of all sports within " + radius + " miles of " + zipCode;

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputString);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius)  throws Exception{

        String outputString = "You want the " + sport + " games within " + radius + " miles of " + zipCode;

        // Need all the radius zip codes
        RadiusCityList zipList = new RadiusCityList(zipCode,radius);
        HashSet<String> zipCities = zipList.findRadiusCities();


        //CALL API to get full season

        GameSchedule schedule = new GameSchedule(sport);
        




        // LOOP AND ONLY RETURN MATCHING CITIES


        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem game: games) {
            if (zipCities.contains(game.getHomeTeam().getCity())) {
                returnGames.add(game);
            }
        }

        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnGames);
        return Response.status(200).entity(output).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sport}/{zip}/{radius}/{fromDate}")
    public Response getMessage(@PathParam("sport") String sport,
                               @PathParam("zip") String zipCode,
                               @PathParam("radius") String radius,
                               @PathParam("fromDate") String fromDate)  throws Exception{

        String outputString = "You want the " + sport + " games within " + radius + " miles of " + zipCode +
                " on or after " + fromDate;

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputString);
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

        String outputString = "You want the " + sport + " games within " + radius + " miles of " + zipCode +
                " between the dates of " + fromDate + " & " + toDate;

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(outputString);
        return Response.status(200).entity(output).build();
    }


}
