package com.bank.transactionservice.feign;

import com.bank.transactionservice.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice",url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping("/api/customer/id/{id}")
    CustomerDto findByCustomerId(@PathVariable("id") Long id);

}
