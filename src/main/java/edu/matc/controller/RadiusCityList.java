package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodeapi.ZipCodesItem;
import com.zipcodeapi.ZipResponse;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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
    private String errorMsg;

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
     * Gets the error message resulting from exception thrown by zipcode api call
     * @return the error message
     */
    public String getErrorMsg() { return errorMsg; }


    /**
     * Sets the error message variable
     *
     * @param errorMsg the error message
     */
    public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }


    /**
     * Handles the api call to find all zip codes within the given radius of the given zip code
     *
     * @return The HashSet of all zip codes within the given radius
     * @throws IOException
     */
    public HashSet<String> findRadiusCities() throws IOException {

        String searchString = "https://www.zipcodeapi.com/rest/3itPa6fYyZJzyc0puZEjvJOwAzbpffOlwLwBmItNEepfUHQzA0zvmyPPikTBXbIi/"
        + "radius.json/" + zipCode + "/" + mileRadius + "/mile";

        String jsonResponse;

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(searchString);

            jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

        } catch (NotFoundException nfe) {
            log.error("Zip code " + getZipCode() + " does not exist");
            setZipCode("Zipcode does not exist");
            return null;

        } catch (Exception e) {
            log.error("Bad or invalid Zipcode " + getZipCode());
            setZipCode("Bad or invalid Zipcode");
            return null;
        }


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
