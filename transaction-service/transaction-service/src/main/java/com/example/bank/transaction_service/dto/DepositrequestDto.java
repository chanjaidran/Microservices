package com.example.bank.transaction_service.dto;

import lombok.Data;

@Data
public class DepositrequestDto {

    private Long accountId;
    private Double amount;
    private String description;
}
