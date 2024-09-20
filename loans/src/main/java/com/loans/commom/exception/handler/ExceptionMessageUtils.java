package com.loans.commom.exception.handler;

import static com.loans.constants.LoansConstants.*;

import com.loans.commom.exception.LoanAlreadyExistsException;
import com.loans.commom.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessageUtils {

    private static MessageSourceAccessor staticMessageSourceAccessor;

    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private ExceptionMessageUtils(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @PostConstruct
    private void init() {
        ExceptionMessageUtils.staticMessageSourceAccessor = this.messageSourceAccessor;
    }

    public static LoanAlreadyExistsException loanAlreadyExistsException(String mobilePhone) {

        return new LoanAlreadyExistsException(
                staticMessageSourceAccessor.getMessage(LOAN_ALREADY_EXISTS_TITLE),
                staticMessageSourceAccessor.getMessage(LOAN_ALREADY_EXISTS_DETAIL, new Object[] {mobilePhone}));
    }

    public static ResourceNotFoundException resourceNotFoundException(String typeOfResource, String identifier) {
        String message = String.format("%s not found for the given data: %s", typeOfResource, identifier);

        return new ResourceNotFoundException(
                staticMessageSourceAccessor.getMessage(RESOURCE_NOT_FOUND_TITLE),
                staticMessageSourceAccessor.getMessage(RESOURCE_NOT_FOUND_DETAIL, new Object[] {message}));
    }
}
