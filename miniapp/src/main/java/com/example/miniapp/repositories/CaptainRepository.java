package com.example.miniapp.repositories;

import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaptainRepository extends JpaRepository<Captain, Long> {
    @Query("SELECT c FROM Captain c WHERE c.avgRatingScore > :threshold")
    List<Captain> findByRatingGreaterThan(Double threshold);

    Captain findByLicenseNumber(String licenseNumber);

}
