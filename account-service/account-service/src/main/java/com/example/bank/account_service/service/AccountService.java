package com.example.bank.account_service.service;

import com.example.bank.account_service.dto.AccountDtoResponse;
import com.example.bank.account_service.dto.CustomerResponse;
import com.example.bank.account_service.entity.Account;
import com.example.bank.account_service.feign.CustomerClient;
import com.example.bank.account_service.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private  CustomerClient customerClient;

    public AccountService(AccountRepository accountRepository,CustomerClient customerClient)
    {
        this.accountRepository=accountRepository;
        this.customerClient=customerClient;
    }



    public Account createAccount(Long customerId, Account account) {

        // Call Customer service
        CustomerResponse customer = customerClient.getCustomerById(customerId);

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        account.setCustomerId(customerId);
        return accountRepository.save(account);
    }

    public Account createAccount(Account account)
    {
        account.setCreatedDate(LocalDate.now());
        return accountRepository.save(account);
    }

    public List<Account> getAccounts()
    {
        return accountRepository.findAll();
    }

    public Optional<Account> findByAccountId(Long id)
    {
        return accountRepository.findById(id);
    }

    public Account findByAccount(Long id)
    {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("Nothing here"));
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }



    public Account updateAccount(Account account, Long id) {

        Optional<Account> accOpt = findByAccountId(id);

        if (accOpt.isPresent()) {
            Account existing = accOpt.get();

            existing.setAccountType(account.getAccountType());
            existing.setBalance(account.getBalance());
            existing.setCustomerId(account.getCustomerId());
            existing.setAccountNumber(account.getAccountNumber());

            return accountRepository.save(existing);
        }

        throw new RuntimeException("Account not found with id " + id);
    }

    public List<AccountDtoResponse> getAccountDtosByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId).stream()
                .map(acc -> new AccountDtoResponse(
                        acc.getId(),
                        acc.getAccountNumber(),
                        acc.getAccountType(),
                        acc.getBalance()
                ))
                .toList();
    }



    public  void addBalance(Long id,Double amount)
    {
        Account account=findByAccount(id);



        account.setBalance(account.getBalance()+amount);

        accountRepository.save(account);

    }
    public  void subBalance(Long id,Double amount)
    {
        Account account=findByAccount(id);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance()-amount);

        accountRepository.save(account);

    }




}
