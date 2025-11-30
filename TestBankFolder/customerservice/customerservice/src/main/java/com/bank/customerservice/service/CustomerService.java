package com.bank.customerservice.service;

import com.bank.customerservice.dto.AccountDto;
import com.bank.customerservice.dto.CustomerDtoResponse;
import com.bank.customerservice.entity.Customer;
import com.bank.customerservice.feign.AccountClient;
import com.bank.customerservice.repository.CustomerRepsitory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    CustomerRepsitory customerRepsitory;

    @Autowired
    AccountClient accountClient;


    public Customer createCustomer(Customer customer){
       return customerRepsitory.save(customer);
    }

    public Customer getCustomerById(Long id)
    {
       return customerRepsitory.findById(id).orElseThrow(()->new RuntimeException("User With Id Not Found"));
    }

    public CustomerDtoResponse getAccountsAndCustomer(Long id)
    {
        Customer customer=getCustomerById(id);
        List<AccountDto> accountDtoList=accountClient.findByCustomerId(id);

        CustomerDtoResponse customerDtoResponse=new CustomerDtoResponse();
        customerDtoResponse.setCustomer(customer);
        customerDtoResponse.setAccountDtoList(accountDtoList);

        return customerDtoResponse;

    }

}
