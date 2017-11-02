package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class HomeTeam{

	// dont show
	@JsonProperty("Abbreviation")
	private String abbreviation;

	// dont show
	@JsonProperty("ID")
	private String iD;

	// show
	@JsonProperty("City")
	private String city;

	// show
	@JsonProperty("Name")
	private String name;

	public void setAbbreviation(String abbreviation){
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation(){
		return abbreviation;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

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