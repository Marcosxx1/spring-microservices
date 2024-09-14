package com.cards.commom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ErrorResponseCustom {

    public ResourceNotFoundException(String title, String detail) {
        super(title, detail, HttpStatus.NOT_FOUND);
    }
}
