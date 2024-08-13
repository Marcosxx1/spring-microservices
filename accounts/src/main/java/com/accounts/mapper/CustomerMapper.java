package com.accounts.mapper;

import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.entity.Customer;

public class CustomerMapper {

    public static PostNewCustomerRequest mapToCustomerDto(
            Customer customer, PostNewCustomerRequest postNewCustomerRequest) {
        postNewCustomerRequest.setEmail(customer.getEmail());
        postNewCustomerRequest.setName(customer.getName());
        postNewCustomerRequest.setMobileNumber(customer.getMobileNumber());

        return postNewCustomerRequest;
    }

    public static Customer mapToCustomer(PostNewCustomerRequest postNewCustomerRequest, Customer customer) {
        customer.setEmail(postNewCustomerRequest.getEmail());
        customer.setName(postNewCustomerRequest.getName());
        customer.setMobileNumber(postNewCustomerRequest.getMobileNumber());

        return customer;
    }
}
