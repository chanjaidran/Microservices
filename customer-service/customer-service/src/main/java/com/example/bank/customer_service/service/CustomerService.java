package com.example.bank.customer_service.service;

import com.example.bank.customer_service.dto.AccountDtoResponse;
import com.example.bank.customer_service.dto.CustomerResponseDto;
import com.example.bank.customer_service.entity.Customer;
import com.example.bank.customer_service.feign.AccountClient;
import com.example.bank.customer_service.repository.CustomerRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;
    AccountClient accountClient;

    public CustomerService(CustomerRepository repo, AccountClient accountClient) {
        this.repo = repo;
        this.accountClient=accountClient;
    }

    public Customer saveCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return repo.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomer(Long id) {
        System.out.println("Fetching from DB, not from cache");

        return repo.findById(id).orElse(null);
    }

    public Customer updateCustomer(Long id, Customer cust) {
        Customer existing = getCustomer(id);
        if (existing == null) return null;

        existing.setName(cust.getName());
        existing.setEmail(cust.getEmail());
        existing.setPhone(cust.getPhone());
        existing.setAddress(cust.getAddress());
        existing.setUpdatedAt(LocalDateTime.now());

        return repo.save(existing);
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }


    public CustomerResponseDto getCustomersAccounts(Long id)
    {
        Customer customer=getCustomer(id);
        List<AccountDtoResponse> accountDtoResponse=accountClient.getAccountsByCustomerId(id);
        CustomerResponseDto customerResponseDto=new CustomerResponseDto();
        customerResponseDto.setCustomer(customer);
        customerResponseDto.setAccountDtoResponses(accountDtoResponse);
        return  customerResponseDto;
    }









}
