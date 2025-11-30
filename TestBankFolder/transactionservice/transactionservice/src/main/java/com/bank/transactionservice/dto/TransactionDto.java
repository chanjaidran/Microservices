package com.bank.transactionservice.dto;

import com.bank.transactionservice.Enum.Transactiontype;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TransactionDto {

    private Long id;
    private Long accountId;
    private Long customerId;
    private String name;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Transactiontype transactiontype;// DEPOSIT, WITHDRAW, TRANSFER
    private Long targetAccountId;
}
