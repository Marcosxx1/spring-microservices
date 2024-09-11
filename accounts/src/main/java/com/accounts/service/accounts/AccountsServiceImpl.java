package com.accounts.service.accounts;

import com.accounts.domain.dto.AccountsDto;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.entity.Accounts;
import com.accounts.domain.entity.Customer;
import com.accounts.helpers.CustomerAccountServiceHelperImpl;
import com.accounts.mapper.AccountsMapper;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAccountServiceHelperImpl helper;

    @Override
    public void createAccount(PostNewCustomerRequest postNewCustomerRequest) {
        Customer customer = CustomerMapper.mapToCustomer(postNewCustomerRequest, new Customer());

        helper.validateCustomerNotExists(customer.getMobileNumber());

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("USER");

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(helper.createNewAccount(savedCustomer));
    }

    @Override
    public CustomerResponse fetchAccount(String mobileNumber) {
        Customer customer = helper.findCustomerByMobileNumber(mobileNumber);
        Accounts account = helper.findAccountByCustomerId(customer.getCustomerId());

        CustomerResponse customerResponse = CustomerMapper.mapToCustomerDto(customer, new CustomerResponse());
        customerResponse.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerResponse;
    }

    @Override
    public boolean updateAccount(CustomerResponse customerResponse) {
        Customer customer = helper.findCustomerByMobileNumber(customerResponse.getMobileNumber());
        Accounts account = helper.findAccountByCustomerId(customer.getCustomerId());

        helper.updateCustomerData(customer, customerResponse);
        helper.updateAccountData(account, customerResponse.getAccountsDto());

        customerRepository.save(customer);
        accountsRepository.save(account);
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = helper.findCustomerByMobileNumber(mobileNumber);
        Accounts account = helper.findAccountByCustomerId(customer.getCustomerId());

        customerRepository.delete(customer);
        accountsRepository.delete(account);
        return true;
    }

    @Override
    public List<Accounts> fetchAll() {
        return accountsRepository.findAll();
    }
}
