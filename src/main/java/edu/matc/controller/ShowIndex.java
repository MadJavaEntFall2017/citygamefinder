package edu.matc.controller;

import com.citygamefinder.SportsItem;
import com.citygamefinder.SportsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysportsfeeds.GameResponse;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

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


/**
 * This is the ShowEmployeeSearchServlet. It will set the page title and forward
 * to the employeeSearch.jsp page.
 *
 *@author lemerson
 */
@WebServlet(
        name = "showIndex",
        urlPatterns = {"/showIndex"}
) public class ShowIndex extends HttpServlet {
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               the HttpRequest
     *@param  response              the HttpResponse
     *@exception  ServletException  if there is a general servlet exception
     *@exception  IOException       if there is a general I/O exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";
        request.setAttribute("title", "CGF Demo");
        request.setAttribute("sports",getSports());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private List<String> getSports() throws IOException {

        String json = callAPI();
        ObjectMapper mapper = new ObjectMapper();
        SportsResponse response = mapper.readValue(json,SportsResponse.class);
        List <SportsItem> listSports = new ArrayList<SportsItem>();
        listSports = response.getSports();

        List<String> availableSports = new ArrayList<String>();
        for (SportsItem currentSport: listSports) {
            availableSports.add(currentSport.getSport());
        }
        return availableSports;
    }

    public String callAPI() throws IOException {
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
        return jsonResponse;
    }
}






