package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodeapi.ZipCodesItem;
import com.zipcodeapi.ZipResponse;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * Handles the calls to the zipcodeapi to get all zip codes within a given radius
 *
 * @author Great Lakes Team
 */
public class RadiusCityList {
    private final Logger log = Logger.getLogger(this.getClass());

    private String zipCode;
    private String mileRadius;
    private Response.StatusType statusInfo;


    /**
     * Generic class constructor
     */
    public RadiusCityList() {

    }

    /**
     * Class constructor with the zip code and mile radius being passed
     *
     * @param zipCode the zip code to be used
     * @param mileRadius the radius to look for cities
     */
    public RadiusCityList(String zipCode, String mileRadius) {
        this.zipCode = zipCode;
        this.mileRadius = mileRadius;
    }

    /**
     * Sets the local zip code variable
     *
     * @param zipCode the zip code to use
     */
    public void setZipCode(String zipCode){this.zipCode = zipCode; }

    /**
     * Gets the local zip code variable
     *
     * @return the local zip code variable
     */
    public String getZipCode(){
        return zipCode;
    }

    /**
     * Sets the local mile radius variable
     *
     * @param mileRadius the mile radius to use
     */
    public void setMileRadius(String mileRadius){
        this.mileRadius = mileRadius;
    }

    /**
     * Gets the local mile radius variable
     * @return the local mile radius variable
     */
    public String getMileRadius(){
        return mileRadius;
    }

    /**
     * Gets the Response status type of the Zip Code API call
     * @return the Response status type of the Zip Code API call
     */
    public Response.StatusType getStatusInfo() {
        return statusInfo;
    }

    /**
     * Handles the api call to find all zip codes within the given radius of the given zip code
     *
     * @return The HashSet of all zip codes within the given radius
     * @throws IOException if there is a general I/O exception
     */
    public HashSet<String> findRadiusCities() throws IOException {

        String searchString = "https://www.zipcodeapi.com/rest/3itPa6fYyZJzyc0puZEjvJOwAzbpffOlwLwBmItNEepfUHQzA0zvmyPPikTBXbIi/"
                + "radius.json/" + zipCode + "/" + mileRadius + "/mile";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(searchString);

        statusInfo = target.request(MediaType.APPLICATION_JSON).get().getStatusInfo();

        if (statusInfo.getFamily() != Response.Status.Family.SUCCESSFUL) {
            log.error("Zip Code API call was not successful, response reason: " + statusInfo);
            log.error("Zip Code API url: " + searchString);
            return null;
        }

        String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        ZipResponse zipResponse = mapper.readValue(jsonResponse,ZipResponse.class);

        HashSet<String> zipCities = new HashSet<String>();
        List <ZipCodesItem> zip = zipResponse.getZipCodes();

        for (ZipCodesItem city: zip) {
            zipCities.add(city.getZipCode());
        }

        return zipCities;
    }


}
