package com.accounts.commom.exception;

import static com.accounts.constants.AccountConstants.*;

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

    public static CustomerAlreadyExistsException customerAlreadyExistsException(String mobilePhone) {
        return new CustomerAlreadyExistsException(
                staticMessageSourceAccessor.getMessage(CUSTOMER_ALREADY_EXISTS_TITLE),
                staticMessageSourceAccessor.getMessage(CUSTOMER_ALREADY_EXISTS_DETAIL, new Object[] {mobilePhone}));
    }

    public static IllegalArgumentException resourceNotFoundException(
            String entityName, String fieldName, String fieldValue) {
        String detailedMessage = String.format("%s not found with %s : '%s'", entityName, fieldName, fieldValue);

        String message =
                staticMessageSourceAccessor.getMessage(RESOURCE_NOT_FOUND_WITH_DATA, new Object[] {detailedMessage});
        return new IllegalArgumentException(message);
    }
}
