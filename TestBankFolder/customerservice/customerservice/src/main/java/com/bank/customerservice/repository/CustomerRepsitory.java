package com.bank.customerservice.repository;

import com.bank.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepsitory extends JpaRepository<Customer,Long> {
}
