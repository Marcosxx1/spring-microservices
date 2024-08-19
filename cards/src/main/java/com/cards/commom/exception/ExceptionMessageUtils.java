package com.cards.commom.exception;

import static com.cards.constants.CardsConstants.*;

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

    public static CardAlreadyExistsException cardAlreadyExistsException() {
        return new CardAlreadyExistsException(staticMessageSourceAccessor.getMessage(RESOURCE_ALREADY_EXISTS));
    }

    public static ResourceNotFoundException resourceNotFoundException(
            String entityName, String fieldName, String fieldValue) {

        String message = staticMessageSourceAccessor.getMessage(
                RESOURCE_NOT_FOUND_WITH_DATA, new Object[] {entityName, fieldName, fieldValue});

        return new ResourceNotFoundException(message);
    }
}
