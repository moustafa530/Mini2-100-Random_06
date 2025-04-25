package com.example.miniapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="captains")
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String licenseNumber;
    private double avgRatingScore;

    @JsonIgnore
    @OneToMany(mappedBy = "captain")
    private List<Trip> trips;

    public Captain(String name, String licenseNumber, double avgRatingScore) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.avgRatingScore = avgRatingScore;
    }
    public Captain(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getlicenseNumber() {
        return licenseNumber;
    }

    public void setlicenseNumber(String liceenseNumber) {
        licenseNumber = liceenseNumber;
    }

    public double getAvgRatingScore() {
        return avgRatingScore;
    }

    public void setAvgRatingScore(double avgRatingScore) {
        avgRatingScore = avgRatingScore;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
