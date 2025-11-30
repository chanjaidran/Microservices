package com.example.bank.customer_service.feign;


import com.example.bank.customer_service.dto.AccountDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account-service",url = "http://localhost:8090")
public interface AccountClient {


    @GetMapping("/api/accounts/customer/{id}")
    List<AccountDtoResponse> getAccountsByCustomerId(@PathVariable("id") Long id);


}
