package com.bank.customerservice.feign;

import com.bank.customerservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "accountservice",url = "http://localhost:8082")
public interface AccountClient {

   @GetMapping("/api/account/customer/{id}")
   List<AccountDto> findByCustomerId(@PathVariable("id")  Long id);
}
