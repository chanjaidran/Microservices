package com.bank.transactionservice.feign;

import com.bank.transactionservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "accountservice",url = "http://localhost:8082")
public interface  AccountClient {

    @GetMapping("/api/account/{id}")
    public AccountDto getAccountById(@PathVariable("id") Long id);



    @PutMapping("/api/account/add/{id}/{amount}")
    public void AddBalance(@PathVariable("id") Long id,@PathVariable("amount") Double amount);


    @PutMapping("/api/account/sub/{id}/{amount}")
    public void SubBalance(@PathVariable("id") Long id,@PathVariable("amount") Double amount);

}
