package com.accounts.helpers;

import com.accounts.commom.exception.ExceptionMessageUtils;
import com.accounts.constants.AccountConstants;
import com.accounts.domain.dto.AccountsDto;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.entity.Accounts;
import com.accounts.domain.entity.Customer;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceHelper {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public void validateCustomerNotExists(String mobileNumber) {
        customerRepository.findByMobileNumber(mobileNumber).ifPresent(c -> {
            throw ExceptionMessageUtils.customerAlreadyExistsException(mobileNumber);
        });
    }

    public Customer findCustomerByMobileNumber(String mobileNumber) {
        return customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("Customer", mobileNumber));
    }

    public Accounts findAccountByCustomerId(Long customerId) {
        return accountsRepository
                .findByCustomerId(customerId)
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("Account", customerId.toString()));
    }

    public void updateCustomerData(Customer customer, CustomerResponse customerResponse) {
        customer.setName(customerResponse.getName());
        customer.setEmail(customerResponse.getEmail());
        customer.setMobileNumber(customerResponse.getMobileNumber());
    }

    public void updateAccountData(Accounts account, AccountsDto accountsDto) {
        account.setAccountType(accountsDto.getAccountType());
        account.setBranchAddress(accountsDto.getBranchAddress());
    }

    public Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(generateRandomAccountNumber());
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    private long generateRandomAccountNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}
