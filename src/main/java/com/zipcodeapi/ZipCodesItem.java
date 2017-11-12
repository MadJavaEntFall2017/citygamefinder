package com.zipcodeapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the zip codes items for the zipcode api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class ZipCodesItem{

	@JsonProperty("distance")
	private int distance;

	@JsonProperty("city")
	private String city;

	@JsonProperty("state")
	private String state;

	@JsonProperty("zip_code")
	private String zipCode;

	/**
	 * Sets the local distance variable
	 *
	 * @param distance the value to set the local distance variable
	 */
	public void setDistance(int distance){
		this.distance = distance;
	}

	/**
	 * Gets the local distance variable
	 *
	 * @return the local distance variable
	 */
	public int getDistance(){
		return distance;
	}

	/**
	 * Sets the local city variable
	 *
	 * @param city the value to set the local city variable
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * Gets the local city variable
	 *
	 * @return the local city variable
	 */
	public String getCity(){
		return city;
	}

	/**
	 * Sets the local state variable
	 *
	 * @param state the value to set the local state variable
	 */
	public void setState(String state){
		this.state = state;
	}

	/**
	 * Gets the local state variable
	 *
	 * @return the local state variable
	 */
	public String getState(){
		return state;
	}

	/**
	 * Sets the local zipCode variable
	 *
	 * @param zipCode the value to set the local zipCode variable
	 */
	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	/**
	 * Gets the local zipCode variable
	 *
	 * @return the local zipCode variable
	 */
	public String getZipCode(){
		return zipCode;
	}

	/**
	 * Returns the local variables into a String value
	 *
	 * @return the String value of all local variables
	 */
	@Override
 	public String toString(){
		return 
			"ZipCodesItem{" + 
			"distance = '" + distance + '\'' + 
			",city = '" + city + '\'' + 
			",state = '" + state + '\'' + 
			",zip_code = '" + zipCode + '\'' + 
			"}";
		}
}