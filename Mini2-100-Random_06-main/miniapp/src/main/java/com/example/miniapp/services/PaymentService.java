package com.example.miniapp.services;

import com.example.miniapp.models.Payment;
import com.example.miniapp.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment updatePayment(long id, Payment payment) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    public void deletePayment(long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> findPaymentsByTripId(long tripId) {
        return paymentRepository.findByTripId(tripId);
    }

    public List<Payment> findByAmountThreshold(Double threshold) {
        return paymentRepository.findByAmountGreaterThan(threshold);
    }
}