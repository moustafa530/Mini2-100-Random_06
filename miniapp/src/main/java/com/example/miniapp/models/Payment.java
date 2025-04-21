package com.example.miniapp.models;

import jakarta.persistence.*;


@Entity
@Table(name = "payments")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String paymentMethod;
    private Boolean paymentStatus;

    @OneToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}