package com.example.miniapp.services;

import com.example.miniapp.models.Trip;
import com.example.miniapp.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Trip updateTrip(Long id, Trip trip) {
        trip.setId(id);
        return tripRepository.save(trip);
    }

    public void deleteTrip(long id) {
        tripRepository.deleteById(id);
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return tripRepository.findTripsBetweenDates(startDate, endDate);
    }

    public List<Trip> findTripsByCaptainId(long captainId) {
        return tripRepository.findByCaptainId(captainId);
    }
}
