package com.example.miniapp.repositories;

import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//done

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    /**
     * Finds captains with an average rating score greater than the specified threshold
     * @param threshold The minimum rating score to filter by
     * @return List of captains meeting the rating criteria
     */
    @Query("SELECT c FROM Captain c WHERE c.avgRatingScore > :threshold")
    List<Captain> findCaptainsWithRatingAbove(Double threshold);

    /**
     * Finds a captain by their unique license number
     * @param licenseNumber The captain's license number
     * @return The matching Captain entity or null if not found
     */
    Captain findByLicenseNumber(String licenseNumber);
}