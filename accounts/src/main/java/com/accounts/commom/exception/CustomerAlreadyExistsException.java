package com.accounts.commom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends ApiException {

    public CustomerAlreadyExistsException(String title, String detail) {
        super(title, detail, HttpStatus.CONFLICT);
    }
}
