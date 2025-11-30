package com.bank.transactionservice.dto;

import com.bank.transactionservice.Enum.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class AccountDto {

    private  Long accountId;
    private  Long customerId;
    private  String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Double balance;
}
