package com.bank.transactionservice.entity;

import com.bank.transactionservice.Enum.Transactiontype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Double amount;
    private Long customerId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Transactiontype transactiontype;// DEPOSIT, WITHDRAW, TRANSFER
    private Long targetAccountId;
}
