package com.example.bank.transaction_service.controller;


import com.example.bank.transaction_service.dto.DepositrequestDto;
import com.example.bank.transaction_service.dto.TransactionResponseDto;
import com.example.bank.transaction_service.dto.TransferRequestDto;
import com.example.bank.transaction_service.dto.WithdrawrequestDto;
import com.example.bank.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class Transactioncontroller {


    @Autowired
    private final TransactionService service;

    @PostMapping("/deposit")
    public TransactionResponseDto deposit(@RequestBody DepositrequestDto req) {
        return service.depsoit(req);
    }

    @PostMapping("/withdraw")
    public TransactionResponseDto withdraw(@RequestBody WithdrawrequestDto req) {
        return service.withdraw(req);
    }

    @PostMapping("/transfer")
    public TransactionResponseDto transfer(@RequestBody TransferRequestDto req) {
        return service.transfer(req);
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionResponseDto> getHistory(@PathVariable Long accountId) {
        return service.getTransactionsByAccount(accountId);
    }

}
