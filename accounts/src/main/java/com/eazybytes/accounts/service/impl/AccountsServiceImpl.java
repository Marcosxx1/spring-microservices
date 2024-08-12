package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.PostNewCustomerRequest;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ExceptionMessageUtils;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(PostNewCustomerRequest postNewCustomerRequest) {
        Customer customer = CustomerMapper.mapToCustomer(postNewCustomerRequest, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) throw ExceptionMessageUtils.customerAlreadyExistsException();

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
        return newAccount;
    }

    @Override
    public PostNewCustomerRequest fetchAccount(String mobileNumber) {
        Customer customer = findOrThrow(customerRepository.findByMobileNumber(mobileNumber), "Customer", "mobileNumber", mobileNumber);
        Accounts account = findOrThrow(accountsRepository.findByCustomerId(customer.getCustomerId()), "Account", "customerId", customer.getCustomerId().toString());

        PostNewCustomerRequest postNewCustomerRequest = CustomerMapper.mapToCustomerDto(customer, new PostNewCustomerRequest());
        postNewCustomerRequest.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return postNewCustomerRequest;
    }

    @Override
    public boolean updateAccount(PostNewCustomerRequest postNewCustomerRequest) {
        Customer customer = findOrThrow(customerRepository.findByMobileNumber(postNewCustomerRequest.getMobileNumber()), "Customer", "mobileNumber", postNewCustomerRequest.getMobileNumber().toString());
        Accounts account = findOrThrow(accountsRepository.findByCustomerId(customer.getCustomerId()), "Account", "customerId", customer.getCustomerId().toString());

        customer.setName(postNewCustomerRequest.getName());
        customer.setEmail(postNewCustomerRequest.getEmail());
        customer.setMobileNumber(postNewCustomerRequest.getMobileNumber());

        account.setAccountType(postNewCustomerRequest.getAccountsDto().getAccountType());
        account.setBranchAddress(postNewCustomerRequest.getAccountsDto().getBranchAddress());

        customerRepository.save(customer);
        accountsRepository.save(account);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = findOrThrow(customerRepository.findByMobileNumber(mobileNumber), "Customer", "mobileNumber", mobileNumber);
        Accounts account = findOrThrow(accountsRepository.findByCustomerId(customer.getCustomerId()), "Account", "customerId", customer.getCustomerId().toString());

        customerRepository.delete(customer);
        accountsRepository.delete(account);

        return true;
    }

    @Override
    public List<Accounts> fetchAll() {
        return accountsRepository.findAll();
    }

    private <T> T findOrThrow(Optional<T> optional, String entityName, String fieldName, String fieldValue) {
        return optional.orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException(entityName, fieldName, fieldValue));
    }
}
