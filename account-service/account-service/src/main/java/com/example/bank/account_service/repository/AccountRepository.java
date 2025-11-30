package com.example.bank.account_service.repository;

import com.example.bank.account_service.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByCustomerId(Long customerId);
}
