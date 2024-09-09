package com.accounts.mapper;

import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.entity.Customer;

public class CustomerMapper {

    public static CustomerResponse mapToCustomerDto(Customer customer, CustomerResponse customerResponse) {
        customerResponse.setEmail(customer.getEmail());
        customerResponse.setName(customer.getName());
        customerResponse.setMobileNumber(customer.getMobileNumber());

        return customerResponse;
    }

    public static Customer mapToCustomer(PostNewCustomerRequest postNewCustomerRequest, Customer customer) {
        customer.setEmail(postNewCustomerRequest.getEmail());
        customer.setName(postNewCustomerRequest.getName());
        customer.setMobileNumber(postNewCustomerRequest.getMobileNumber());

        return customer;
    }
}
