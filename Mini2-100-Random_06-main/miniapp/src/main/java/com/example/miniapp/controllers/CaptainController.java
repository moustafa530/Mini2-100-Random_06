package com.example.miniapp.controllers;

import com.example.miniapp.models.Captain;
import com.example.miniapp.services.CaptainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/captains")
public class CaptainController {

    private final CaptainService captainService;

    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    // Create a new captain
    @PostMapping
    public Captain createCaptain(@RequestBody Captain captain) {
        return captainService.addCaptain(captain);
    }

    // Get all captains
    @GetMapping
    public List<Captain> getAllCaptains() {
        return captainService.getAllCaptains();
    }

    // Get captain by ID
    @GetMapping("/{id}")
    public Captain getCaptainById(@PathVariable Long id) {
        return captainService.getCaptainById(id);
    }

    // Filter captains by minimum rating
    @GetMapping("/filter/rating")
    public List<Captain> filterCaptainsByRating(
            @RequestParam(name = "minRating") Double ratingThreshold) {
        return captainService.getCaptainsByRating(ratingThreshold);
    }

    // Find captain by license number
    @GetMapping("/filter/license")
    public Captain findCaptainByLicenseNumber(
            @RequestParam(name = "licenseNumber") String licenseNumber) {
        return captainService.getCaptainByLicenseNumber(licenseNumber);
    }
}