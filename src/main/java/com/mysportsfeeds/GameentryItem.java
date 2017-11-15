package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * This class holds the game entry item for the mysportfeeds api
 *
 * @author Great Lakes Team
 */
@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameentryItem{


	@JsonProperty("date")
	private String date;

	@JsonProperty("originalTime")
	private Object originalTime;

	@JsonProperty("awayTeam")
	private AwayTeam awayTeam;

	@JsonProperty("originalDate")
	private Object originalDate;

	@JsonProperty("delayedOrPostponedReason")
	private Object delayedOrPostponedReason;

	@JsonProperty("scheduleStatus")
	private String scheduleStatus;

	@JsonProperty("homeTeam")
	private HomeTeam homeTeam;

	@JsonProperty("location")
	private String location;

	@JsonProperty("zipCode")
	private String zipCode;

	@JsonProperty("id")
	private String id;

	@JsonProperty("time")
	private String time;

	/**
	 * Sets the local date variable
	 *
	 * @param date the value to set the local date variable
	 */
	public void setDate(String date){
		this.date = date;
	}

	/**
	 * Gets the local date variable
	 *
	 * @return the local date variable
	 */
	public String getDate(){
		return date;
	}

	/**
	 * Sets the local originalTime variable
	 *
	 * @param originalTime the value to set the local originalTime variable
	 */
	public void setOriginalTime(Object originalTime){
		this.originalTime = originalTime;
	}

	/**
	 * Gets the local originalTime variable
	 *
	 * @return the local originalTime variable
	 */
	public Object getOriginalTime(){
		return originalTime;
	}

	/**
	 * Sets the local awayTeam variable
	 *
	 * @param awayTeam the value to set the local awayTeam variable
	 */
	public void setAwayTeam(AwayTeam awayTeam){
		this.awayTeam = awayTeam;
	}

	/**
	 * Gets the local awayTeam variable
	 *
	 * @return the local awayTeam variable
	 */
	public AwayTeam getAwayTeam(){
		return awayTeam;
	}

	/**
	 * Sets the local originalDate variable
	 *
	 * @param originalDate the value to set the originalDate variable
	 */
	public void setOriginalDate(Object originalDate){
		this.originalDate = originalDate;
	}

	/**
	 * Gets the local originalDate variable
	 *
	 * @return the local originalDate variable
	 */
	public Object getOriginalDate(){
		return originalDate;
	}

	/**
	 * Sets the local delayedOrPostponedReason variable
	 *
	 * @param delayedOrPostponedReason the value to set the local delayedOrPostponedReason
	 */
	public void setDelayedOrPostponedReason(Object delayedOrPostponedReason){
		this.delayedOrPostponedReason = delayedOrPostponedReason;
	}

	/**
	 * Gets the local delayedOrPostponedReason variable
	 *
	 * @return the local delayedOrPostponedReason variable
	 */
	public Object getDelayedOrPostponedReason(){
		return delayedOrPostponedReason;
	}

	/**
	 * Sets the local scheduleStatus variable
	 *
	 * @param scheduleStatus the value to set the local scheduleStatus variable
	 */
	public void setScheduleStatus(String scheduleStatus){
		this.scheduleStatus = scheduleStatus;
	}

	/**
	 * Gets the local scheduleStatus
	 *
	 * @return the local scheduleStatus variable
	 */
	public String getScheduleStatus(){
		return scheduleStatus;
	}

	/**
	 * Sets the local homeTeam variable
	 *
	 * @param homeTeam the value to set the local homeTeam variable
	 */
	public void setHomeTeam(HomeTeam homeTeam){
		this.homeTeam = homeTeam;
	}

	/**
	 * Gets the local homeTeam variable
	 *
	 * @return the local homeTeam variable
	 */
	public HomeTeam getHomeTeam(){
		return homeTeam;
	}

	/**
	 * Sets the local location variable
	 *
	 * @param location the value to set the local location variable
	 */
	public void setLocation(String location){
		this.location = location;
	}

	/**
	 * Gets the local location variable
	 *
	 * @return the local location variable
	 */
	public String getLocation(){
		return location;
	}

	/**
	 * Sets the local id variable
	 *
	 * @param id the value to set the local id variable
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * Gets the local id variable
	 *
	 * @return the local id variable
	 */
	public String getId(){
		return id;
	}

	/**
	 * Sets the local time variable
	 *
	 * @param time the value to set the local time variable
	 */
	public void setTime(String time){
		this.time = time;
	}

	/**
	 * Gets the local time variable
	 *
	 * @return the local time variable
	 */
	public String getTime(){
		return time;
	}

	/**
	 * Gets the local zipCode variable
	 *
	 * @return the local zipCode variable
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the local zipCode variable
	 *
	 * @param zipCode the value to set the local zipCode variable
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Returns the local variables into a String value
	 *
	 * @return the String value of all local variables
	 */
	@Override
 	public String toString(){
		return 
			"GameentryItem{" + 
			"date = '" + date + '\'' + 
			",originalTime = '" + originalTime + '\'' + 
			",awayTeam = '" + awayTeam + '\'' + 
			",originalDate = '" + originalDate + '\'' + 
			",delayedOrPostponedReason = '" + delayedOrPostponedReason + '\'' + 
			",scheduleStatus = '" + scheduleStatus + '\'' + 
			",homeTeam = '" + homeTeam + '\'' + 
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}