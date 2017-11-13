package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.Fullgameschedule;
import com.mysportsfeeds.GameentryItem;
import edu.matc.entity.Error;
import edu.matc.entity.Result;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the ShowResults servlet. It will call the API using the form date, create a list of result objects,
 * set the page title and forward to the results.jsp page.
 *
 *@author lemerson
 */
@WebServlet(
        name = "showResults",
        urlPatterns = {"/showResults"}
) public class ShowResults extends HttpServlet {

    private Error error;

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

        error = new Error();
        String zipCode = request.getParameter("zipcode-input");
        String fromDate = request.getParameter("from-date-input");
        String toDate = request.getParameter("to-date-input");
        String miles = request.getParameter("mile-input");
        String sport = request.getParameter("sport-input");

        String callUrl = buildUrl(sport,zipCode,miles,fromDate,toDate);
        Fullgameschedule schedule = callService(callUrl);

        String url = "";
        if (error.getHasError()) {
            url = "/error.jsp";
            request.setAttribute("error", error);
        } else {
            url = "/results.jsp";
            request.setAttribute("games", loadResults(schedule));
            request.setAttribute("title", "Search Results");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Builds the url needed for the api call
     *
     * @param sport the sport to use for the api
     * @param zip the zip code to use for the api
     * @param miles the miles to use for the api
     * @param fromDate the from date to use for the api
     * @param toDate the to date to use for the api
     * @return the url for the api call
     */
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

    /**
     * Sets up the list of Results from the api
     *
     * @param schedule the Fullgameschedule for the api
     * @return the list of Results from the api
     */
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

    /**
     * Calls the api to get the Fullgameschedule
     *
     * @param url the url to use to call the api
     * @return the Fullgameschedule returned from the api
     * @throws IOException if there is a general I/O exception
     */
    public Fullgameschedule callService(String url) throws IOException {

        String jsonResponse = "";
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(url);
            jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        } catch (Exception e) {
            error.setHasError(true);
            error.setMessage(e.getMessage());
            error.setUrl(url);
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonResponse,Fullgameschedule.class);
    }
}