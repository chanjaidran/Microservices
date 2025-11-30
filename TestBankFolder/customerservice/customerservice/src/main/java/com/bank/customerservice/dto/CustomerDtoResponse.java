package com.bank.customerservice.dto;

import com.bank.customerservice.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDtoResponse {

    Customer customer;
    List<AccountDto> accountDtoList;
}
