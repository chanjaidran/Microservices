package com.example.bank.customer_service.controller;

import com.example.bank.customer_service.dto.CustomerResponseDto;
import com.example.bank.customer_service.entity.Customer;
import com.example.bank.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/customer")
    public Customer create(@RequestBody Customer c) {
        return service.saveCustomer(c);
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return service.getCustomer(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer c) {
        return service.updateCustomer(id, c);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "Customer deleted";
    }

    @GetMapping("/details/{id}")
    public CustomerResponseDto getDetails(@PathVariable Long id) {
        return service.getCustomersAccounts(id);
    }
}
