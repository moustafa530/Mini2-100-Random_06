package com.example.miniapp.repository;

import com.example.miniapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByTripId(Long tripId);
    List<Payment> findByAmountGreaterThan(Double amount);
}
