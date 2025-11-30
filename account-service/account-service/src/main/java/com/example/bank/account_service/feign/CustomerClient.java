package com.example.bank.account_service.feign;


import com.example.bank.account_service.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service",url = "http://localhost:8081")
public interface CustomerClient {


    @GetMapping("/api/customers/{id}")
    CustomerResponse getCustomerById(@PathVariable Long id);


}
