package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodeapi.ZipCodesItem;
import com.zipcodeapi.ZipResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class RadiusCityList {

    private String zipCode;
    private String mileRadius;

    public RadiusCityList() {

    }

    public RadiusCityList(String zipCode, String mileRadius) {
        this.zipCode = zipCode;
        this.mileRadius = mileRadius;
    }

    public void setZipCode(String zipCode){this.zipCode = zipCode; }

    public String getZipCode(){
        return zipCode;
    }

    public void setMileRadius(String mileRadius){
        this.mileRadius = mileRadius;
    }

    public String getMileRadius(){
        return mileRadius;
    }


    public HashSet<String> findRadiusCities() throws IOException {

        String searchString = "https://www.zipcodeapi.com/rest/3itPa6fYyZJzyc0puZEjvJOwAzbpffOlwLwBmItNEepfUHQzA0zvmyPPikTBXbIi/"
        + "radius.json/" + zipCode + "/" + mileRadius + "/mile";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(searchString);

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
