package com.mysportsfeeds;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the game entry item for the citygamefinder api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class Fullgameschedule{

	@JsonProperty("lastUpdatedOn")
	private String lastUpdatedOn;

	@JsonProperty("gameentry")
	private List<GameentryItem> gameentry;

    /**
     * Sets the local lastUpdatedOn variable
     *
     * @param lastUpdatedOn the value to set the local lastUpdatedOn variable
     */
	public void setLastUpdatedOn(String lastUpdatedOn){
		this.lastUpdatedOn = lastUpdatedOn;
	}

    /**
     * Gets the local lastUpdatedOn variable
     *
     * @return the local lastUpdatedOn variable
     */
	public String getLastUpdatedOn(){
		return lastUpdatedOn;
	}

    /**
     * Sets the local gameentry variable
     *
     * @param gameentry the value to set the local gameentry variable
     */
	public void setGameentry(List<GameentryItem> gameentry){
		this.gameentry = gameentry;
	}

    /**
     * Gets the local gameentry variable
     *
     * @return the local gameentry variable
     */
	public List<GameentryItem> getGameentry(){
		return gameentry;
	}

    /**
     * Returns the local variables into a String value
     *
     * @return the String value of all local variables
     */
	@Override
 	public String toString(){
		return 
			"Fullgameschedule{" + 
			"lastUpdatedOn = '" + lastUpdatedOn + '\'' + 
			",gameentry = '" + gameentry + '\'' + 
			"}";
		}
}