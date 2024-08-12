package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByMobileNumber(String mobileNumber);
}
