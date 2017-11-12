package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the game response for the mysportsfeeds api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class GameResponse {

	@JsonProperty("fullgameschedule")
	private Fullgameschedule fullgameschedule;

	/**
	 * Sets the local fullgameschedule variable
	 *
	 * @param fullgameschedule the value to set the local fullgameschedule variable
	 */
	public void setFullgameschedule(Fullgameschedule fullgameschedule){
		this.fullgameschedule = fullgameschedule;
	}

	/**
	 * Gets the local fullgameschedule variable
	 *
	 * @return the local fullgameschedule variable
	 */
	public Fullgameschedule getFullgameschedule(){
		return fullgameschedule;
	}

	/**
	 * Returns the local variables into a String value
	 *
	 * @return the String value of all local variables
	 */
	@Override
 	public String toString(){
		return 
			"GameResponse{" +
			"fullgameschedule = '" + fullgameschedule + '\'' + 
			"}";
		}
}