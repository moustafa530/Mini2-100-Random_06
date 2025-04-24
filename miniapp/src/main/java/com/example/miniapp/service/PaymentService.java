package com.example.miniapp.service;

import com.example.miniapp.model.Payment;
import com.example.miniapp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePayment(Long id, Payment payment) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> findPaymentsByTripId(Long tripId) {
        return paymentRepository.findByTripId(tripId);
    }

    public List<Payment> findPaymentsWithAmountGreaterThan(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}
