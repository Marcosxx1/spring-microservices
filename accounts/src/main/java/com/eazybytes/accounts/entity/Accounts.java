package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Accounts extends BaseEntity{

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="account_number")
    @Id
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

}
