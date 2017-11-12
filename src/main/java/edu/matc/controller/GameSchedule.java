package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import edu.matc.entity.Stadiums;
import edu.matc.persistence.StadiumsDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the code for getting the game schedule for a specific sport
 *
 * @author Great Lakes Team
 */
public class GameSchedule {

    private String sport;

    /**
     * Generic class constructor
     */
    public GameSchedule() {

    }

    /**
     * Class constructor with a single string input
     *
     * @param sport The sport to get the schedule for
     */
    public GameSchedule(String sport) {
        this.sport = sport;
    }

    /**
     * Returns the String value for the internal sport variable
     *
     * @return the sport
     */
    public String getSport() {
        return sport;
    }

    /**
     * Sets the String value for the internal sport variable
     *
     * @param sport the sport to find the schedule for
     */
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * Main processing for getting the schedule for a given sport
     *
     * @return a list of GameentryItems
     * @throws IOException if there is a general I/O exception
     */
    public List<GameentryItem> getSchedule() throws IOException {

        List<GameentryItem> gameSchedule = new ArrayList<GameentryItem>();

        try {
            GameResponse response = gameApiCall();
            gameSchedule = updateZip(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gameSchedule;
    }

    /**
     * Sets up and calls the api using the internal sport variable and returns the api GameResponse object
     *
     * @return the GameResponse from the api call
     * @throws IOException if there is a general I/O exception
     */
    public GameResponse gameApiCall () throws IOException {

        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/" + sport
                + "/current/full_game_schedule.json");
        String encoding = Base64.encode ("madentjava2017:greatlakes".getBytes());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in = new BufferedReader (new InputStreamReader(content));
        String jsonResponse="";
        String line;
        while ((line = in.readLine()) != null) {
            jsonResponse = jsonResponse + line;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonResponse,GameResponse.class);
    }

    /**
     * Uses the GameResponse to find and set the zip code for all games for the full game schedule
     *
     * @param response The GameResponse item from the api
     * @return The GameentryItem from the api but with updated zip codes
     */
    public List<GameentryItem> updateZip (GameResponse response)  {

        StadiumsDao dao = new StadiumsDao();
        List<Stadiums> stadiums = dao.getAllStadiums();

        List <GameentryItem> gameDetails = response.getFullgameschedule().getGameentry();

        for (GameentryItem game: gameDetails) {
            game.setZipCode(findStadiumZip(game.getLocation(), stadiums));
        }

        return gameDetails;
    }

    /**
     * Uses the stadium name and and list of stadiums to loop through find the zip code for the stadium name
     *
     * @param stadiumName the name of the stadium
     * @param stadiums the list of all possible stadiums
     * @return the zip code for the matched stadium
     */
    public String findStadiumZip (String stadiumName, List<Stadiums> stadiums) {

        String zipCode = "";
        for (Stadiums currentStadium: stadiums) {
            if (stadiumName.equals(currentStadium.getStadiumName())) {
                zipCode = currentStadium.getZipCode();
            }
        }

        return zipCode;

    }

}
