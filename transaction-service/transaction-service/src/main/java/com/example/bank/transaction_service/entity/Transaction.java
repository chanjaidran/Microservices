package com.example.bank.transaction_service.entity;

import com.example.bank.transaction_service.enumm.AccountType;
import com.example.bank.transaction_service.enumm.Transactiontype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Transactiontype type;// DEPOSIT, WITHDRAW, TRANSFER
    private LocalDateTime dateTime;
    private Long targetAccountId; // only for transfers
}
