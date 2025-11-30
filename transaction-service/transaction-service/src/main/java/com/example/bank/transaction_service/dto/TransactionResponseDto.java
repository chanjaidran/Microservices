package com.example.bank.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private Long accountId;
    private Double amount;
    private String type;
    private LocalDateTime transactionDate;
    private Long targetAccountId;
}
