package com.loans.commom.exception;

import static com.loans.constants.LoansConstants.*;

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

    public static LoanAlreadyExistsException loanAlreadyExistsException() {
        return new LoanAlreadyExistsException(staticMessageSourceAccessor.getMessage(MESSAGE_404));
    }

    public static IllegalArgumentException resourceNotFoundException(
            String entityName, String fieldName, String fieldValue) {

        String message = staticMessageSourceAccessor.getMessage(
                RESOURCE_NOT_FOUND_WITH_DATA, new Object[] {entityName, fieldName, fieldValue});

        return new IllegalArgumentException(message);
    }

    public static IllegalArgumentException resourceAlreadyExistsException() {
        return new IllegalArgumentException(staticMessageSourceAccessor.getMessage(RESOURCE_ALREADY_EXISTS));
    }
}
