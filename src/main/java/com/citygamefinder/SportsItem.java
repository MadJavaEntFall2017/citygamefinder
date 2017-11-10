package com.citygamefinder;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SportsItem{

	@JsonProperty("sport")
	private String sport;

	public void setSport(String sport){
		this.sport = sport;
	}

	public String getSport(){
		return sport;
	}

	@Override
 	public String toString(){
		return 
			"SportsItem{" + 
			"sport = '" + sport + '\'' + 
			"}";
		}
}