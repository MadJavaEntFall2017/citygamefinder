package edu.matc.entity;

import javax.persistence.*;

@Entity
@Table(name = "stadiums")
public class Stadiums implements java.io.Serializable {

    @Id
    @Column(name = "stadium_name")
    private String stadiumName;

    @Column(name = "zip_code")
    private String zipCode;

    public Stadiums() {

    }

    public Stadiums(String stadiumName, String zipCode) {
        this.stadiumName = stadiumName;
        this.zipCode = zipCode;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}