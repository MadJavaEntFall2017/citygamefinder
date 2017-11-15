import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zipcodeapi.ZipCodesItem;
import com.zipcodeapi.ZipResponse;
import edu.matc.controller.GameSchedule;
import edu.matc.controller.RadiusCityList;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ApiTest {

    @Test
    public void dateTest() throws Exception {
        //Integer dateInt = Integer.valueOf(currentGame.getDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate gameLocalDate = LocalDate.parse("2017-11-08", formatter);
        LocalDate fromLocalDate = LocalDate.parse("2017-11-09", formatter);

        if (gameLocalDate.isEqual(fromLocalDate)) {
            System.out.println(gameLocalDate + " is equal to " + fromLocalDate);
        }
        if (gameLocalDate.isAfter(fromLocalDate)) {
            System.out.println(gameLocalDate + " is after to " + fromLocalDate);
        }
        if (gameLocalDate.isBefore(fromLocalDate)) {
            System.out.println(gameLocalDate + " is before to " + fromLocalDate);
        }

    }

    @Test
    public void SportsApiTest() throws Exception {

        try {
            //https://api.mysportsfeeds.com/v1.1/pull/nhl/2016-2017-regular/full_game_schedule.json
            URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nfl/current/full_game_schedule.json");

            String encoding = Base64.encode ("madentjava2017:greatlakes".getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in   =
                    new BufferedReader (new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch(Exception e) {            e.printStackTrace();
        }

    }

    @Test
    public void ZipApiTest() throws Exception {

        String searchString = "https://www.zipcodeapi.com/rest/3itPa6fYyZJzyc0puZEjvJOwAzbpffOlwLwBmItNEepfUHQzA0zvmyPPikTBXbIi/"
                + "radius.json/" + "53529" + "/" + "10" + "/mile";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(searchString);

        String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        ZipResponse zipResponse = mapper.readValue(jsonResponse,ZipResponse.class);

    }

    @Test
    public void OjbectApiTest() throws Exception {
        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nfl/current/full_game_schedule.json");
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
        GameResponse gameResponse = mapper.readValue(jsonResponse,GameResponse.class);
    }

    @Test
    public void UniqueSportCity() throws Exception {


        HashSet<String> sportsCities = new HashSet<String>();

        URL url = new URL("https://api.mysportsfeeds.com/v1.1/pull/nfl/current/full_game_schedule.json");

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
        GameResponse gameResponse= mapper.readValue(jsonResponse,GameResponse.class);

        for (GameentryItem game : gameResponse.getFullgameschedule().getGameentry()) {
            sportsCities.add(game.getLocation());
        }

        String line2 = "2332";
    }


    @Test
    public void TestGroupAPI() throws Exception {

        RadiusCityList zipList = new RadiusCityList("53718","200");
        HashSet<String> zipCities = zipList.findRadiusCities();

        GameSchedule schedule = new GameSchedule("nfl");
        List<GameentryItem> games = schedule.getSchedule();

        List<GameentryItem> returnGames = new ArrayList<GameentryItem>();
        for (GameentryItem currentGame: games) {
            if (zipCities.contains(currentGame.getZipCode())) {
                returnGames.add(currentGame);
            }
        }

        ObjectMapper returnMapper = new ObjectMapper();
        String output = returnMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnGames);
    }

    @Test
    public void gettingSports() throws Exception {
        List<String> sports = new ArrayList<String>();
        URL url = new URL("http://13.59.5.68:8080/citygamefinder/sports");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in = new BufferedReader (new InputStreamReader(content));
        String jsonResponse="";
        String line;
        while ((line = in.readLine()) != null) {
            jsonResponse = jsonResponse + line;
        }
        String bob = "bob";

    }


}
