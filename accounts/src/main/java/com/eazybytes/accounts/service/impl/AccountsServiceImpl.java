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
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new customer account based on the information provided in the {@code customerDto} object.
     * The method maps the {@code CustomerDto} object to a {@code Customer} entity using the
     * {@link CustomerMapper#mapToCustomer(CustomerDto, Customer)} method and saves the customer
     * information to the database via the {@code customerRepository}.
     *
     * @param customerDto The {@code CustomerDto} object containing the information for creating the account.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) throw new CustomerAlreadyExistsException("Customer already registered.");

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("someone");
        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("someone");

        return newAccount;

    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Customer",
                        "mobileNumber",
                        mobileNumber
                )
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Account",
                        "customerId",
                        customer.getCustomerId().toString()
                )
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        return customerDto;
    }
}
