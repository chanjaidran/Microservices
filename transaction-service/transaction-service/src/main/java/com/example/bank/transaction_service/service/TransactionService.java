package com.example.bank.transaction_service.service;

import com.example.bank.transaction_service.dto.*;
import com.example.bank.transaction_service.entity.Transaction;
import com.example.bank.transaction_service.enumm.Transactiontype;
import com.example.bank.transaction_service.feign.AccountClient;
import com.example.bank.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class TransactionService {

    @Autowired
    AccountClient accountClient;

    @Autowired
    TransactionRepository transactionRepository;


    private TransactionResponseDto mapToDto(Transaction tx) {
        return new TransactionResponseDto(
                tx.getId(),
                tx.getAccountId(),
                tx.getAmount(),
                tx.getType().name(),
                tx.getDateTime(),
                tx.getTargetAccountId()
        );
    }


    public TransactionResponseDto depsoit(DepositrequestDto depositrequestDto)

    {
        accountClient.addBalance(depositrequestDto.getAccountId(), depositrequestDto.getAmount());

      Transaction transaction=new Transaction();
      transaction.setAccountId(depositrequestDto.getAccountId());
      transaction.setAmount(depositrequestDto.getAmount());
      transaction.setDateTime(LocalDateTime.now());
      transaction.setType(Transactiontype.DEPOSIT);

      transactionRepository.save(transaction);

     return mapToDto(transaction);

    }


    public TransactionResponseDto withdraw(WithdrawrequestDto withdrawrequestDto)
    {

        AccountDtoResponse accountDtoResponse=accountClient.getAccount(withdrawrequestDto.getAccountId());

                if(accountDtoResponse.getBalance()<0)
                {
                    throw  new RuntimeException("No Proper Balance");
                }
                accountClient.subtractBalance(withdrawrequestDto.getAccountId(), withdrawrequestDto.getAmount());
                Transaction tx = new Transaction();
        tx.setAccountId(withdrawrequestDto.getAccountId());
        tx.setAmount(withdrawrequestDto.getAmount());
        tx.setType(Transactiontype.WITHDRAW);
        tx.setDateTime(LocalDateTime.now());
        transactionRepository.save(tx);
        return mapToDto(tx);

    }


    public  TransactionResponseDto transfer(TransferRequestDto transferRequestDto)
    {

        AccountDtoResponse accountDtoResponse=accountClient.getAccount(transferRequestDto.getFromAccountId());

        if (accountDtoResponse.getBalance() < transferRequestDto.getAmount()) {
            throw new RuntimeException("Insufficient balance for transfer");
        }

        accountClient.subtractBalance(transferRequestDto.getFromAccountId(), transferRequestDto.getAmount());
        accountClient.addBalance(transferRequestDto.getToAccountId(),transferRequestDto.getAmount());
        Transaction transaction=new Transaction();
        transaction.setAccountId(transferRequestDto.getFromAccountId());
        transaction.setTargetAccountId(transferRequestDto.getToAccountId());
        transaction.setAmount(transaction.getAmount());
        transaction.setType(Transactiontype.TRANSFER);
        transactionRepository.save(transaction);
        return mapToDto(transaction);

    }


    public List<TransactionResponseDto> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }



}
