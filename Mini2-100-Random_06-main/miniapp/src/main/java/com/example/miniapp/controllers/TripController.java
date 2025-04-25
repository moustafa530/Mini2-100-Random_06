package com.example.miniapp.controllers;

import com.example.miniapp.models.Trip;
import com.example.miniapp.services.TripService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip) {
        return tripService.addTrip(trip);
    }

    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
    }

    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        try {
            startDateTime = LocalDateTime.parse(startDate);
            endDateTime = LocalDateTime.parse(endDate);
        } catch (Exception e) {
            LocalDate startLocalDate = LocalDate.parse(startDate);
            LocalDate endLocalDate = LocalDate.parse(endDate);
            startDateTime = startLocalDate.atStartOfDay();
            endDateTime = endLocalDate.atTime(LocalTime.MAX);
        }

        return tripService.findTripsWithinDateRange(startDateTime, endDateTime);
    }

    @GetMapping("/findByCaptainId")
    public List<Trip> findTripsByCaptainId(@RequestParam Long captainId) {
        return tripService.findTripsByCaptainId(captainId);
    }
}