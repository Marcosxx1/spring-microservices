package com.accounts.service.accounts;

import com.accounts.commom.exception.ExceptionMessageUtils;
import com.accounts.constants.AccountConstants;
import com.accounts.domain.dto.AccountsDto;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.entity.Accounts;
import com.accounts.domain.entity.Customer;
import com.accounts.mapper.AccountsMapper;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
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
        if (optionalCustomer.isPresent())
            throw ExceptionMessageUtils.customerAlreadyExistsException(customer.getMobileNumber());

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("USER");

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
    public CustomerResponse fetchAccount(String mobileNumber) {
        Customer customer = findOrThrow(
                customerRepository.findByMobileNumber(mobileNumber), "Customer", "mobileNumber", mobileNumber);
        Accounts account = findOrThrow(
                accountsRepository.findByCustomerId(customer.getCustomerId()),
                "Account",
                "customerId",
                customer.getCustomerId().toString());

        CustomerResponse customerResponse = CustomerMapper.mapToCustomerDto(customer, new CustomerResponse());
        customerResponse.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerResponse;
    }

    @Override
    public boolean updateAccount(CustomerResponse customerResponse) {
        Customer customer = findOrThrow(
                customerRepository.findByMobileNumber(customerResponse.getMobileNumber()),
                "Customer",
                "mobileNumber",
                customerResponse.getMobileNumber());
        Accounts account = findOrThrow(
                accountsRepository.findByCustomerId(customer.getCustomerId()),
                "Account",
                "customerId",
                customer.getCustomerId().toString());

        customer.setName(customerResponse.getName());
        customer.setEmail(customerResponse.getEmail());
        customer.setMobileNumber(customerResponse.getMobileNumber());

        account.setAccountType(customerResponse.getAccountsDto().getAccountType());
        account.setBranchAddress(customerResponse.getAccountsDto().getBranchAddress());

        customerRepository.save(customer);
        accountsRepository.save(account);

        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = findOrThrow(
                customerRepository.findByMobileNumber(mobileNumber), "Customer", "mobileNumber", mobileNumber);
        Accounts account = findOrThrow(
                accountsRepository.findByCustomerId(customer.getCustomerId()),
                "Account",
                "customerId",
                customer.getCustomerId().toString());

        customerRepository.delete(customer);
        accountsRepository.delete(account);

        return true;
    }

    @Override
    public List<Accounts> fetchAll() {
        return accountsRepository.findAll();
    }

    private <T> T findOrThrow(Optional<T> optional, String entityName, String fieldName, String fieldValue) {
        return optional.orElseThrow(
                () -> ExceptionMessageUtils.resourceNotFoundException(entityName, fieldName, fieldValue));
    }
}
