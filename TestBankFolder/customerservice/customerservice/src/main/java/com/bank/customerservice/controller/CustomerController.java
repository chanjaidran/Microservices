package com.bank.customerservice.controller;

import com.bank.customerservice.dto.CustomerDtoResponse;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }


    @GetMapping("/id/{id}")
    public Customer findByCustomerId(@PathVariable("id") Long id)
    {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customers/{id}/accounts")
    public CustomerDtoResponse customerDtoResponse(@PathVariable("id") Long id)
    {
        return customerService.getAccountsAndCustomer(id);
    }


}
