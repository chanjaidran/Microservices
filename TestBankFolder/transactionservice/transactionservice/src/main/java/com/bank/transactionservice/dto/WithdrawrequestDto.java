package com.bank.transactionservice.dto;


import lombok.Data;

@Data
public class WithdrawrequestDto {
    private Long accountId;
    private Double amount;
    private Long customerId;
    private String description;
}
