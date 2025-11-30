package com.example.bank.transaction_service.dto;


import lombok.Data;

@Data
public class WithdrawrequestDto {
    private Long accountId;
    private Double amount;
    private String description;
}
