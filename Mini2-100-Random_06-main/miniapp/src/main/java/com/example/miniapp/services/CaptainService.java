package com.example.miniapp.services;

import com.example.miniapp.models.Captain;
import com.example.miniapp.repositories.CaptainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaptainService {

    private final CaptainRepository captainRepository;

    public CaptainService(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }

    // Create operations
    public Captain createCaptain(Captain captain) {
        return captainRepository.save(captain);
    }

    // Read operations
    public List<Captain> retrieveAllCaptains() {
        return captainRepository.findAll();
    }

    public Optional<Captain> retrieveCaptainById(Long id) {
        return captainRepository.findById(id);
    }

    public List<Captain> retrieveCaptainsAboveRating(double minRating) {
        return captainRepository.findByRatingGreaterThan(minRating);
    }

    public Optional<Captain> retrieveCaptainByLicense(String licenseNumber) {
        return Optional.ofNullable(captainRepository.findByLicenseNumber(licenseNumber));
    }
}