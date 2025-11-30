package com.bank.transactionservice.service;


import com.bank.transactionservice.Enum.Transactiontype;
import com.bank.transactionservice.dto.*;
import com.bank.transactionservice.entity.Transaction;
import com.bank.transactionservice.feign.AccountClient;
import com.bank.transactionservice.feign.CustomerClient;
import com.bank.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountClient accountClient;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerClient customerClient;


    public TransactionDto maptoDto(Transaction transaction)
    {
        TransactionDto transactionDto=new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAccountId(transaction.getAccountId());
        transactionDto.setCustomerId(transaction.getCustomerId());
        transactionDto.setName(transaction.getName());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactiontype(transaction.getTransactiontype());
        transactionDto.setTargetAccountId(transaction.getTargetAccountId());

        return transactionDto;
    }


    public TransactionDto depositTransaction(DepositrequestDto depositrequestDto)
    {
        CustomerDto customerDto=customerClient.findByCustomerId(depositrequestDto.getCustomerId());
        AccountDto accountDto=accountClient.getAccountById(depositrequestDto.getAccountId());
        accountClient.AddBalance(depositrequestDto.getAccountId(),depositrequestDto.getAmount());

        Transaction transaction=new Transaction();
        transaction.setAccountId(depositrequestDto.getAccountId());
        transaction.setAmount(depositrequestDto.getAmount());
        transaction.setTransactiontype(Transactiontype.DEPOSIT);
        transaction.setCustomerId(accountDto.getCustomerId());
        transaction.setName(customerDto.getName());
        transactionRepository.save(transaction);
        return maptoDto(transaction);

    }


    public TransactionDto withdrawTransaction(WithdrawrequestDto withdrawrequestDto)
    {

        CustomerDto customerDto=customerClient.findByCustomerId(withdrawrequestDto.getCustomerId());
        AccountDto accountDto=accountClient.getAccountById(withdrawrequestDto.getAccountId());

        if(accountDto.getBalance()<0)
        {
            throw  new RuntimeException("No Proper Balance");
        }

        accountClient.SubBalance(withdrawrequestDto.getAccountId(),withdrawrequestDto.getAmount());

        Transaction transaction=new Transaction();
        transaction.setAccountId(withdrawrequestDto.getAccountId());
        transaction.setAmount(withdrawrequestDto.getAmount());
        transaction.setTransactiontype(Transactiontype.WITHDRAW);
        transaction.setCustomerId(accountDto.getCustomerId());
        transaction.setName(customerDto.getName());
        transactionRepository.save(transaction);
        return maptoDto(transaction);

    }


    public TransactionDto transerTransaction(TransferRequestDto transferRequestDto)
    {
        CustomerDto customerDto=customerClient.findByCustomerId(transferRequestDto.getCustomerId());

            AccountDto accountDto=accountClient.getAccountById(transferRequestDto.getFromAccountId());

        if(accountDto.getBalance()<0)
        {
            throw  new RuntimeException("No Proper Balance");
        }
        accountClient.SubBalance(transferRequestDto.getFromAccountId(),transferRequestDto.getAmount());

        accountClient.AddBalance(transferRequestDto.getToAccountId(),transferRequestDto.getAmount());

        Transaction transaction=new Transaction();
        transaction.setAccountId(transferRequestDto.getFromAccountId());
        transaction.setTargetAccountId(transferRequestDto.getToAccountId());
        transaction.setTransactiontype(Transactiontype.TRANSFER);
        transaction.setCustomerId(accountDto.getCustomerId());
        transaction.setName(customerDto.getName());
        transaction.setAmount(transferRequestDto.getAmount());
        transactionRepository.save(transaction);

        return maptoDto(transaction);



    }


  public   List<TransactionDto> getAccountTransactionsByAccountId(Long id)
    {
        return  transactionRepository.findByAccountId(id).stream().map(this::maptoDto).toList();
    }

}
