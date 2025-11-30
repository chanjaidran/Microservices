package com.bank.accountservice.controller;


import com.bank.accountservice.entity.Account;
import com.bank.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("{id}/create")
    public Account createAccount(@PathVariable("id") Long id, @RequestBody Account account)
    {
      return   accountService.createAccount(account,id);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long id)
    {
        return accountService.getAccountById(id);
    }

    @GetMapping("/customer/{id}")
    public List<Account> findByCustomerId(@PathVariable("id")  Long id)
    {
        return accountService.findByCustomerId(id);
    }

    @PutMapping("add/{id}/{amount}")
    public void AddBalance(@PathVariable("id") Long id,@PathVariable("amount") Double amount)
    {
        accountService.AddBalance(id,amount);
    }

    @PutMapping("sub/{id}/{amount}")
    public void SubBalance(@PathVariable("id") Long id,@PathVariable("amount") Double amount)
    {
        accountService.SubBalance(id,amount);
    }
}
