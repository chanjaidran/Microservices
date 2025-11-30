package com.bank.transactionservice.controller;

import com.bank.transactionservice.dto.DepositrequestDto;
import com.bank.transactionservice.dto.TransactionDto;
import com.bank.transactionservice.dto.TransferRequestDto;
import com.bank.transactionservice.dto.WithdrawrequestDto;
import com.bank.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/deposit")
    public TransactionDto deposit(@RequestBody DepositrequestDto req) {
        return transactionService.depositTransaction(req);
    }

    @PostMapping("/withdraw")
    public TransactionDto withdraw(@RequestBody WithdrawrequestDto req) {
        return transactionService.withdrawTransaction(req);
    }

    @PostMapping("/transfer")
    public TransactionDto transfer(@RequestBody TransferRequestDto req) {
        return transactionService.transerTransaction(req);
    }

    @GetMapping("/{id}")
    public List<TransactionDto> getAccountTransactionsByAccountId(@PathVariable("id") Long id)
    {
        return transactionService.getAccountTransactionsByAccountId(id);
    }

}
