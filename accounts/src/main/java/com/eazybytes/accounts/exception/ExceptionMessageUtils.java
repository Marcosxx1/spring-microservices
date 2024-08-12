package com.eazybytes.accounts.exception;

import static com.eazybytes.accounts.constants.AccountConstants.MESSAGE_404;

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

    public static CustomerAlreadyExistsException customerAlreadyExistsException() {
        return new CustomerAlreadyExistsException(staticMessageSourceAccessor.getMessage(MESSAGE_404));
    }

    /*    public static ResourceNotFoundException resourceNotFoundException(String entityName, String fieldName, String fieldValue) {
        String message = String.format(
                "%s not found with %s : '%s'",
                entityName, fieldName, fieldValue
        );
        return new staticMessageSourceAccessor.getMessage(MESSAGE_404, new Object[]{message}));
    }*/

}
