package com.example.bank.transaction_service.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String description;
}
