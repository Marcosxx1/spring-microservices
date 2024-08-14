package com.loans.controller;

import com.loans.constants.LoansConstants;
import com.loans.domain.dto.LoansContactInfoDto;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.dto.Response;
import com.loans.service.LoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoansControllerImpl implements LoansController {

    private final LoansService iLoansService;
    private final Environment environment;
    private final LoansContactInfoDto loansContactInfoDto;
    private final MessageSourceAccessor staticMessageSourceAccessor;

    @Value("${build.version}")
    private String buildVersion;

    @Override
    public ResponseEntity<Response> createLoan(String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(
                        HttpStatus.CREATED, staticMessageSourceAccessor.getMessage(LoansConstants.MESSAGE_201)));
    }

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String mobileNumber) {
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Override
    public ResponseEntity<Response> updateLoanDetails(LoansDto loansDto) {
        boolean isUpdated = iLoansService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(LoansConstants.MESSAGE_200)));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(
                            HttpStatus.EXPECTATION_FAILED,
                            staticMessageSourceAccessor.getMessage(LoansConstants.MESSAGE_417_UPDATE)));
        }
    }

    @Override
    public ResponseEntity<Response> deleteLoanDetails(String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(LoansConstants.MESSAGE_200)));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(
                            HttpStatus.EXPECTATION_FAILED,
                            staticMessageSourceAccessor.getMessage(LoansConstants.MESSAGE_417_DELETE)));
        }
    }

    @Override
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Override
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Override
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto);
    }
}
