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

public class GameSchedule {

    private String sport;

    public GameSchedule() {

    }

    public GameSchedule(String sport) {
        this.sport = sport;
    }

    public List<GameentryItem> getSchedule() throws IOException {

        List<GameentryItem> gameSchedule = new ArrayList<GameentryItem>();

        try {
            GameResponse response = gameApiCall(sport);
            gameSchedule = updateZip(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gameSchedule;
    }


    public GameResponse gameApiCall (String sport) throws IOException {

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

    public List<GameentryItem> updateZip (GameResponse response)  {

        StadiumsDao dao = new StadiumsDao();
        List<Stadiums> stadiums = dao.getAllStadiums();

        List <GameentryItem> gameDetails = response.getFullgameschedule().getGameentry();

        for (GameentryItem game: gameDetails) {
            game.setZipCode(findStadiumZip(game.getLocation(), stadiums));
        }

        return gameDetails;
    }

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
