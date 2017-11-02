package com.mysportsfeeds;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameentryItem{

	// show
	@JsonProperty("date")
	private String date;

	// dont show
	@JsonProperty("originalTime")
	private Object originalTime;

	// show
	@JsonProperty("awayTeam")
	private AwayTeam awayTeam;

	// dont show
	@JsonProperty("originalDate")
	private Object originalDate;

	// dont show
	@JsonProperty("delayedOrPostponedReason")
	private Object delayedOrPostponedReason;

	// dont show
	@JsonProperty("scheduleStatus")
	private String scheduleStatus;

	// show
	@JsonProperty("homeTeam")
	private HomeTeam homeTeam;

	// show
	@JsonProperty("location")
	private String location;

	// dont show
	@JsonProperty("id")
	private String id;

	// show
	@JsonProperty("time")
	private String time;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setOriginalTime(Object originalTime){
		this.originalTime = originalTime;
	}

	public Object getOriginalTime(){
		return originalTime;
	}

	public void setAwayTeam(AwayTeam awayTeam){
		this.awayTeam = awayTeam;
	}

	public AwayTeam getAwayTeam(){
		return awayTeam;
	}

	public void setOriginalDate(Object originalDate){
		this.originalDate = originalDate;
	}

	public Object getOriginalDate(){
		return originalDate;
	}

	public void setDelayedOrPostponedReason(Object delayedOrPostponedReason){
		this.delayedOrPostponedReason = delayedOrPostponedReason;
	}

	public Object getDelayedOrPostponedReason(){
		return delayedOrPostponedReason;
	}

	public void setScheduleStatus(String scheduleStatus){
		this.scheduleStatus = scheduleStatus;
	}

	public String getScheduleStatus(){
		return scheduleStatus;
	}

	public void setHomeTeam(HomeTeam homeTeam){
		this.homeTeam = homeTeam;
	}

	public HomeTeam getHomeTeam(){
		return homeTeam;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

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