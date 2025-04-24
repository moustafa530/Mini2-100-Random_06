package com.example.miniapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String paymentMethod;
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip; // Make sure Trip entity exists
}
