package com.citygamefinder;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the sport value for the citygamefinder api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class SportsItem{

	@JsonProperty("sport")
	private String sport;

    /**
     * Sets the local sport variable
     *
     * @param sport the value to set the local sport variable
     */
	public void setSport(String sport){
		this.sport = sport;
	}

    /**
     * Gets the local sport variable
     *
     * @return the local sport variable
     */
	public String getSport(){
		return sport;
	}

    /**
     * Returns the local variables into a String value
     *
     * @return the String value of all local variables
     */
	@Override
 	public String toString(){
		return 
			"SportsItem{" + 
			"sport = '" + sport + '\'' + 
			"}";
		}
}