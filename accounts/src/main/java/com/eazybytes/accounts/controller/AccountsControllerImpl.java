package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountsControllerImpl implements AccountsController {

    private final IAccountsService iAccountsService;

    @Override
    public ResponseEntity<ResponseDto> createAccount(CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<CustomerDto> fetchAccountDetails(String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Override
    public ResponseEntity<List<Accounts>> fetchAllAcounts() {
        List<Accounts> accounts = iAccountsService.fetchAll();

        if (!accounts.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accounts);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateAccount(CustomerDto customerDto) {
        boolean updated = iAccountsService.updateAccount(customerDto);
        if (updated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(AccountConstants.STATUS_404, AccountConstants.MESSAGE_404));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAccount(String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_204, AccountConstants.MESSAGE_204));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("400", "something went wrong"));
        }
    }
}
