package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the home team values for the mysportsfeeds api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
public class HomeTeam{

	@JsonProperty("Abbreviation")
	private String abbreviation;

	@JsonProperty("ID")
	private String iD;

	@JsonProperty("City")
	private String city;

	@JsonProperty("Name")
	private String name;

	/**
	 * Sets the local abbreviation variable
	 *
	 * @param abbreviation the value to set the local abbreviation variable
	 */
	public void setAbbreviation(String abbreviation){
		this.abbreviation = abbreviation;
	}

	/**
	 * Gets the local abbreviation variable
	 *
	 * @return the local abbrebiation variable
	 */
	public String getAbbreviation(){
		return abbreviation;
	}

	/**
	 * Sets the local iD variable
	 *
	 * @param iD the value to set the local iD variable
	 */
	public void setID(String iD){
		this.iD = iD;
	}

	/**
	 * Gets the local iD variable
	 *
	 * @return the local iD variable
	 */
	public String getID(){
		return iD;
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
	 * Sets the local name variable
	 *
	 * @param name the value to set the local name variable
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the local name variable
	 *
	 * @return the local name variable
	 */
	public String getName(){
		return name;
	}

	/**
	 * Returns the local variables into a String value
	 *
	 * @return the String value of all local variables
	 */
	@Override
 	public String toString(){
		return 
			"HomeTeam{" + 
			"abbreviation = '" + abbreviation + '\'' + 
			",iD = '" + iD + '\'' + 
			",city = '" + city + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}