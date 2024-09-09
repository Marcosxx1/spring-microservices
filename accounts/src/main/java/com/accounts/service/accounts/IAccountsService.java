package com.accounts.service.accounts;

import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.entity.Accounts;
import java.util.List;

public interface IAccountsService {

    void createAccount(PostNewCustomerRequest postNewCustomerRequest);

    CustomerResponse fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerResponse customerResponse);

    boolean deleteAccount(String mobileNumber);

    List<Accounts> fetchAll();
}
