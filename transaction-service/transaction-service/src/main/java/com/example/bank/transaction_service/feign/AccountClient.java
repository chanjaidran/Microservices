package com.example.bank.transaction_service.feign;


import com.example.bank.transaction_service.dto.AccountDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service", url = "http://localhost:8090")
public interface AccountClient {

    @GetMapping("/api/accounts/{id}")
    AccountDtoResponse getAccount(@PathVariable("id") Long id);

    @PutMapping("/api/accounts/{id}/balance/add")
    void addBalance(@PathVariable("id") Long id, @RequestParam Double amount);

    @PutMapping("/api/accounts/{id}/balance/subtract")
    void subtractBalance(@PathVariable("id") Long id, @RequestParam Double amount);
}
