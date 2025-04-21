package com.example.miniapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String LiceenseNumber;
    private double AvgRatingScore;

    @JsonIgnore
    @OneToMany(mappedBy = "captain")
    private List<Trip> trips;


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

    public String getLiceenseNumber() {
        return LiceenseNumber;
    }

    public void setLiceenseNumber(String liceenseNumber) {
        LiceenseNumber = liceenseNumber;
    }

    public double getAvgRatingScore() {
        return AvgRatingScore;
    }

    public void setAvgRatingScore(double avgRatingScore) {
        AvgRatingScore = avgRatingScore;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
