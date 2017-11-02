package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class GameResponse {

	@JsonProperty("fullgameschedule")
	private Fullgameschedule fullgameschedule;

	public void setFullgameschedule(Fullgameschedule fullgameschedule){
		this.fullgameschedule = fullgameschedule;
	}

	public Fullgameschedule getFullgameschedule(){
		return fullgameschedule;
	}

	@Override
 	public String toString(){
		return 
			"GameResponse{" +
			"fullgameschedule = '" + fullgameschedule + '\'' + 
			"}";
		}
}