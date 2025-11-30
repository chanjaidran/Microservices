package com.bank.accountservice.service;


import com.bank.accountservice.dto.CustomerDto;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.feign.CustomerClient;
import com.bank.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerClient customerClient;

    public Account createAccount(Account account,Long id)
    {
        CustomerDto customerDto=customerClient.findByCustomerId(id);
        account.setCustomerId(customerDto.getCustomerId());
        return accountRepository.save(account);

    }

    public Account getAccountById(Long id)
    {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
    }

    public List<Account> findByCustomerId(Long id)
    {
        return accountRepository.findByCustomerId(id);
    }

    public void AddBalance(Long id,Double amount)
    {

        Account account=getAccountById(id);
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

    }

    public void SubBalance(Long id,Double amount)
    {

        Account account=getAccountById(id);
        if(account.getBalance()<amount)
        {
            throw new RuntimeException("Amount Not Sufficient");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);

    }
}
