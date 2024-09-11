package com.accounts.helpers;

import com.accounts.domain.dto.AccountsDto;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.entity.Accounts;
import com.accounts.domain.entity.Customer;

public interface CustomerAccountServiceHelper {

    void validateCustomerNotExists(String mobileNumber);

    Customer findCustomerByMobileNumber(String mobileNumber);

    Accounts findAccountByCustomerId(Long customerId);

    void updateCustomerData(Customer customer, CustomerResponse customerResponse);

    void updateAccountData(Accounts account, AccountsDto accountsDto);

    Accounts createNewAccount(Customer customer);
}
