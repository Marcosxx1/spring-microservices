package com.eazybytes.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.accounts.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends  JpaRepository<Customer, Integer>{

    Optional<Customer> findByMobileNumber(String mobileNumber);

}
