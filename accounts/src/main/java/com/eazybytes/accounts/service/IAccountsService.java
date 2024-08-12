package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.PostNewCustomerRequest;
import com.eazybytes.accounts.entity.Accounts;
import java.util.List;

public interface IAccountsService {

    void createAccount(PostNewCustomerRequest postNewCustomerRequest);

    PostNewCustomerRequest fetchAccount(String mobileNumber);

    boolean updateAccount(PostNewCustomerRequest postNewCustomerRequest);

    boolean deleteAccount(String mobileNumber);

    List<Accounts> fetchAll();
}
