package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.Fullgameschedule;
import com.mysportsfeeds.GameResponse;
import com.mysportsfeeds.GameentryItem;
import edu.matc.entity.Result;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This is the ShowEmployeeSearchServlet. It will set the page title and forward
 * to the employeeSearch.jsp page.
 *
 *@author lemerson
 */
@WebServlet(
        name = "showResults",
        urlPatterns = {"/showResults"}
) public class ShowResults extends HttpServlet {
    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpRequest
     * @param response the HttpResponse
     * @throws ServletException if there is a general servlet exception
     * @throws IOException      if there is a general I/O exception
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String zipCode = request.getParameter("zipcode-input");
        String fromDate = request.getParameter("from-date-input");
        String toDate = request.getParameter("to-date-input");
        String miles = request.getParameter("mile-input");
        String sport = request.getParameter("sport-input");

        String callUrl = buildUrl(sport,zipCode,miles,fromDate,toDate);
        Fullgameschedule schedule = callService(callUrl);

        request.setAttribute("games", loadResults(schedule));
        request.setAttribute("title", "Search Results");
        String url = "/results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    public String buildUrl(String sport, String zip, String miles, String fromDate, String toDate)  {
        StringBuilder urlCall = new StringBuilder();
        urlCall.append("http://13.59.5.68:8080/citygamefinder/sports");

        if (sport != null && !sport.equals("")) {
            urlCall.append("/" + sport);
        }

        if (zip != null && !zip.equals("")) {
            urlCall.append("/" + zip);
        }

        if (miles != null && !miles.equals("")) {
            urlCall.append("/" + miles);
        }

        if (fromDate != null && !fromDate.equals("")) {
            urlCall.append("/" + fromDate);
        }

        if (toDate != null && !toDate.equals("")) {
            urlCall.append("/" + toDate);
        }

        return urlCall.toString();

    }

    public List<Result> loadResults (Fullgameschedule schedule) {

        List<GameentryItem> games = new ArrayList<GameentryItem>();
        games = schedule.getGameentry();

        List<Result> results = new ArrayList<Result>();
        for (GameentryItem currentGame: games) {
            Result result = new Result();
            result.setAwayTeam(currentGame.getAwayTeam().getName());
            result.setDate(currentGame.getDate());
            result.setHomeTeam(currentGame.getHomeTeam().getName());
            result.setLocation(currentGame.getLocation());
            result.setTime(currentGame.getTime());
            result.setZipCode(currentGame.getZipCode());
            results.add(result);
        }
        return results;
    }

    public Fullgameschedule callService(String url) throws IOException {

        URL callUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) callUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in = new BufferedReader (new InputStreamReader(content));
        String jsonResponse="";
        String line;
        while ((line = in.readLine()) != null) {
            jsonResponse = jsonResponse + line;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonResponse,Fullgameschedule.class);
    }
}



