package edu.matc.entity;

import javax.persistence.*;

/**
 * The hibernate entity class for the stadiums table
 *
 * @author Great Lakes Team
 */
@Entity
@Table(name = "stadiums")
public class Stadiums implements java.io.Serializable {

    @Id
    @Column(name = "stadium_name")
    private String stadiumName;

    @Column(name = "zip_code")
    private String zipCode;

    /**
     * Generic class constructor
     */
    public Stadiums() {

    }

    /**
     * Class constructor that will set the stadium name and zip code
     *
     * @param stadiumName the stadium name to set the local stadiumName variable
     * @param zipCode the zip code to set the local zipCode variable
     */
    public Stadiums(String stadiumName, String zipCode) {
        this.stadiumName = stadiumName;
        this.zipCode = zipCode;
    }

    /**
     * Gets the local stadiumName variable
     *
     * @return the local stadiumName variable
     */
    public String getStadiumName() {
        return stadiumName;
    }

    /**
     * Sets the local stadiumName variable
     *
     * @param stadiumName the value to set the local stadiumName variable
     */
    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
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
     * Sets the local zipCode variable
     *
     * @param zipCode the value to set the local zipCode variable
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}