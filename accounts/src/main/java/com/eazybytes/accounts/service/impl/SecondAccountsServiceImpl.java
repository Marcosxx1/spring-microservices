package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


/**
 * Implementation of the {@code IAccountsService} interface for managing customer accounts.
 * This service is responsible for creating customer accounts based on the provided
 * {@code CustomerDto} object.
 */
@Service
@AllArgsConstructor
public class SecondAccountsServiceImpl{

    public Integer printNumber() {
        return 1;
    }

    public void createAccount(CustomerDto customerDto) {

    }

    public CustomerDto fetchAccount(String mobileNumber) {
        return null;
    }

    public boolean updateAccount(CustomerDto customerDto) {
        return false;
    }

    public boolean updateAccountV2(CustomerDto customerDto) {
        return false;
    }


}
