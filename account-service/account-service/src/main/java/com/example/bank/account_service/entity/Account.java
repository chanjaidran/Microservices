package com.example.bank.account_service.entity;

import com.example.bank.account_service.Enum.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;  // Foreign key to CustomerService (but no direct relation here!)

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double balance;

    private LocalDate createdDate;
}
