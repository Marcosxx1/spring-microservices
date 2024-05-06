package com.eazybytes.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.accounts.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends  JpaRepository<Customer, Integer>{
}
