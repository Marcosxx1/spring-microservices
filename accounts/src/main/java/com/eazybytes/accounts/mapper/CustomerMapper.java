package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.PostNewCustomerRequest;
import com.eazybytes.accounts.entity.Customer;

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
