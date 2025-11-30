package com.bank.transactionservice.dto;

import lombok.Data;

@Data
public class TransferRequestDto {
    private Long fromAccountId;
    private Long toAccountId;
    private Long customerId;
    private Double amount;
    private String description;
}
