package com.example.bank.transaction_service.dto;


import com.example.bank.transaction_service.enumm.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDtoResponse {

    private Long id;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Double balance;
}
