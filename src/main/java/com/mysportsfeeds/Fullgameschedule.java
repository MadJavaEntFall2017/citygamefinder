package com.mysportsfeeds;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Fullgameschedule{

	@JsonProperty("lastUpdatedOn")
	private String lastUpdatedOn;

	@JsonProperty("gameentry")
	private List<GameentryItem> gameentry;

	public void setLastUpdatedOn(String lastUpdatedOn){
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getLastUpdatedOn(){
		return lastUpdatedOn;
	}

	public void setGameentry(List<GameentryItem> gameentry){
		this.gameentry = gameentry;
	}

	public List<GameentryItem> getGameentry(){
		return gameentry;
	}

	@Override
 	public String toString(){
		return 
			"Fullgameschedule{" + 
			"lastUpdatedOn = '" + lastUpdatedOn + '\'' + 
			",gameentry = '" + gameentry + '\'' + 
			"}";
		}
}