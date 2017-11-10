package com.citygamefinder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SportsResponse{

	@JsonProperty("sports")
	private List<SportsItem> sports;

	public void setSports(List<SportsItem> sports){
		this.sports = sports;
	}

	public List<SportsItem> getSports(){
		return sports;
	}

	@Override
 	public String toString(){
		return 
			"SportsResponse{" + 
			"sports = '" + sports + '\'' + 
			"}";
		}
}