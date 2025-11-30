package com.example.bank.customer_service.dto;

import com.example.bank.customer_service.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Customer customer;
    private List<AccountDtoResponse> accountDtoResponses;
}
