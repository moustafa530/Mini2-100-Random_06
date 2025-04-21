package com.example.miniapp.repositories;

import com.example.miniapp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("SELECT t FROM Trip t WHERE t.tripDate BETWEEN :start AND :end")
    List<Trip> findTripsBetweenDates(LocalDateTime start, LocalDateTime end);

    List<Trip> findByCaptainId(Long captainId);

}
