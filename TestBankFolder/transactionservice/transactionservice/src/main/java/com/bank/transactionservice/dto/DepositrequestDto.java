package com.bank.transactionservice.dto;

import lombok.Data;

@Data
public class DepositrequestDto {

    private Long accountId;
    private Long customerId;
    private Double amount;
    private String description;
}
