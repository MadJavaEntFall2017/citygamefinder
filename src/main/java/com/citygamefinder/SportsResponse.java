package com.citygamefinder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the SportsItem for the citygamefinder api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class SportsResponse{

	@JsonProperty("sports")
	private List<SportsItem> sports;

    /**
     * Sets the local sports variable
     *
     * @param sports the variable to set the local sports variable
     */
	public void setSports(List<SportsItem> sports){
		this.sports = sports;
	}

    /**
     * Gets the local sports variable
     *
     * @return the local sports variable
     */
	public List<SportsItem> getSports(){
		return sports;
	}

    /**
     * Returns the local variables into a String value
     *
     * @return the String value of all local variables
     */
	@Override
 	public String toString(){
		return 
			"SportsResponse{" + 
			"sports = '" + sports + '\'' + 
			"}";
		}
}