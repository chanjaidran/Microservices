package com.bank.accountservice.entity;

import com.bank.accountservice.Enum.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long accountId;
    private  Long customerId;
    private  String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Double balance;
}
