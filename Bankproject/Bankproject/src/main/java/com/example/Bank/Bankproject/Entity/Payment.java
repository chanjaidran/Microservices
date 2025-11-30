package com.example.Bank.Bankproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long customerId;
    private Long accountId;

    private Double amount;
    private String paymentMethod; // UPI, CARD, NETBANKING

    private String externalReferenceId;
    private String status;

    private LocalDateTime createdAt;
}

