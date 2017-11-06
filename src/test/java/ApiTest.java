import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zipcodeapi.ZipCodesItem;
import com.zipcodeapi.ZipResponse;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

public class ApiTest {

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
            sportsCities.add(game.getHomeTeam().getCity());
        }



    }

}
