package com.accounts.controller;

import com.accounts.constants.AccountConstants;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.dto.ResponseDto;
import com.accounts.domain.entity.Accounts;
import com.accounts.service.IAccountsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountsControllerImpl implements AccountsController {

    private final IAccountsService iAccountsService;
    private final MessageSourceAccessor staticMessageSourceAccessor;

    @Override
    public ResponseEntity<ResponseDto> createAccount(PostNewCustomerRequest postNewCustomerRequest) {
        iAccountsService.createAccount(postNewCustomerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        HttpStatus.CREATED, staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_201)));
    }

    @Override
    public ResponseEntity<PostNewCustomerRequest> fetchAccountDetails(String mobileNumber) {
        PostNewCustomerRequest postNewCustomerRequest = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(postNewCustomerRequest);
    }

    @Override
    public ResponseEntity<List<Accounts>> fetchAllAcounts() {
        List<Accounts> accounts = iAccountsService.fetchAll();

        if (!accounts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateAccount(PostNewCustomerRequest postNewCustomerRequest) {
        boolean updated = iAccountsService.updateAccount(postNewCustomerRequest);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_200)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(
                            HttpStatus.NOT_FOUND,
                            staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_404)));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAccount(String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.NO_CONTENT, AccountConstants.MESSAGE_204));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.NOT_FOUND, "something went wrong"));
        }
    }
}
