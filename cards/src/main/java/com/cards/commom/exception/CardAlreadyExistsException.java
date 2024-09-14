package com.cards.commom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends ErrorResponseCustom {

    public CardAlreadyExistsException(String title, String detail) {
        super(title, detail, HttpStatus.CONFLICT);
    }
}
