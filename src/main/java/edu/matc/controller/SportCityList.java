package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

public class SportCityList {

    public SportCityList() {

    }

    public HashSet<String> findSportCities() throws IOException {

        HashSet<String> sportsCities = new HashSet<String>();

        try {
            findNflTeams(sportsCities);
            findNbaTeams(sportsCities);
            findMlbTeams(sportsCities);
            findNhlTeams(sportsCities);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sportsCities;
    }


    public void findNflTeams (HashSet<String> sportsCities) throws IOException {
        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nfl/current/full_game_schedule.json");
        GameResponse gameResponse = gameApiCall(url);
        addUniqueCiies(gameResponse.getFullgameschedule().getGameentry(),sportsCities);
    }

    public void findNbaTeams (HashSet<String> sportsCities) throws IOException{
        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nba/current/full_game_schedule.json");
        GameResponse gameResponse = gameApiCall(url);
        addUniqueCiies(gameResponse.getFullgameschedule().getGameentry(),sportsCities);
    }

    public void findMlbTeams (HashSet<String> sportsCities) throws IOException{

        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/mlb/2017-regular/full_game_schedule.json");
        GameResponse gameResponse = gameApiCall(url);
        addUniqueCiies(gameResponse.getFullgameschedule().getGameentry(),sportsCities);
    }

    public void findNhlTeams (HashSet<String> sportsCities) throws IOException {
        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nhl/current/full_game_schedule.json");
        GameResponse gameResponse = gameApiCall(url);
        addUniqueCiies(gameResponse.getFullgameschedule().getGameentry(),sportsCities);
    }

    public GameResponse gameApiCall (URL url) throws IOException {
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

    public void addUniqueCiies (List<GameentryItem> gameList, HashSet<String> sportsCities) {
        for (GameentryItem game : gameList) {
            sportsCities.add(game.getHomeTeam().getCity());
        }
    }

}
