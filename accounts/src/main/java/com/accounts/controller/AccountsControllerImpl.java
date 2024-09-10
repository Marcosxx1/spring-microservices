package com.accounts.controller;

import com.accounts.constants.AccountConstants;
import com.accounts.domain.dto.AccountContactInfo;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.dto.ResponseDto;
import com.accounts.domain.entity.Accounts;
import com.accounts.service.accounts.IAccountsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/api",
        produces = {"application/json"})
public class AccountsControllerImpl implements AccountsController {

    private final IAccountsService iAccountsService;
    private final MessageSourceAccessor staticMessageSourceAccessor;
    private final Environment environment;
    private final AccountContactInfo accountContactInfo;

    @Value("${build.version:}")
    private String build;

    @Override
    public ResponseEntity<ResponseDto> createAccount(PostNewCustomerRequest postNewCustomerRequest) {
        log.info("POST on createAccount() /create - Creating account for request: {}", postNewCustomerRequest);

        iAccountsService.createAccount(postNewCustomerRequest);
        log.info("Account created successfully for request: {}", postNewCustomerRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        HttpStatus.CREATED, staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_201)));
    }

    @Override
    public ResponseEntity<CustomerResponse> fetchAccountDetails(String mobileNumber) {
        log.info("GET on fetchAccountDetails() /fetch - Fetching account details for mobile number: {}", mobileNumber);

        CustomerResponse customerResponse = iAccountsService.fetchAccount(mobileNumber);
        log.info("Fetched account details: {}", customerResponse);

        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @Override
    public ResponseEntity<List<Accounts>> fetchAllAcounts() {
        log.info("GET on fetchAllAcounts() /fetch-all - Fetching all accounts");
        List<Accounts> accounts = iAccountsService.fetchAll();

        if (!accounts.isEmpty()) {

            log.info("Fetched {} accounts", accounts.size());
            return ResponseEntity.status(HttpStatus.OK).body(accounts);

        } else {

            log.warn("No accounts found");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateAccount(CustomerResponse customerResponse) {
        log.info("PUT on updateAccount() /update - Updating account for request: {}", customerResponse);
        boolean updated = iAccountsService.updateAccount(customerResponse);
        if (updated) {
            log.info("Account updated successfully for request: {}", customerResponse);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_200)));
        } else {
            log.warn("Account not found for request: {}", customerResponse);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(
                            HttpStatus.NOT_FOUND,
                            staticMessageSourceAccessor.getMessage(AccountConstants.MESSAGE_404)));
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAccount(String mobileNumber) {
        log.info("DELETE on deleteAccount() /delete - Deleting account for mobile number: {}", mobileNumber);
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            log.info("Account deleted successfully for mobile number: {}", mobileNumber);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.NO_CONTENT, AccountConstants.MESSAGE_204));
        } else {
            log.warn("Failed to delete account for mobile number: {}", mobileNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.NOT_FOUND, "something went wrong"));
        }
    }

    @Override
    public ResponseEntity<String> getBuildInfo() {
        log.info("GET on getBuildInfo() /build-info - Fetching build info");
        return ResponseEntity.status(HttpStatus.OK).body(build);
    }

    @Override
    public ResponseEntity<String> getJavaVersion() {
        log.info("GET on getJavaVersion() /java-version - java version");
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Override
    public ResponseEntity<AccountContactInfo> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountContactInfo);
    }
}
