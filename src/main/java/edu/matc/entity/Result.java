package edu.matc.entity;

/**
 * This class will hold all the input data for the citygamefinder call
 *
 * @author Great Lakes Team
 */
public class Result {
    private String date;
    private String location;
    private String zipCode;
    private String time;
    private String homeTeam;
    private String awayTeam;

    /**
     * Get the local date variable
     *
     * @return the local date variable
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the local date variable
     *
     * @param date the date to set the local date variable
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the local location variable
     *
     * @return the local location variable
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the local location variable
     *
     * @param location the location to set the local location variable
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the local zipCode variable
     *
     * @return the local zipCode variable
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Set the local zipCode variable
     *
     * @param zipCode the zipCode to set the local zipCode variable
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the local time variable
     *
     * @return the local time variable
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the local time variable
     *
     * @param time the time to set the local time variable
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the local homeTeam variable
     *
     * @return the local hometeam variable
     */
    public String getHomeTeam() {
        return homeTeam;
    }

    /**
     * Set the local homeTeam variable
     *
     * @param homeTeam the homeTeam to set the local homeTeam variable
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Get the local awayTeam variable
     *
     * @return the local awayTeam variable
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * Set the local awayTeam variable
     *
     * @param awayTeam the awayTeam to set the local awayTeam variable
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}
