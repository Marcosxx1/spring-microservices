package com.loans.commom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends ApiException {

    public LoanAlreadyExistsException(String title, String detail) {
        super(title, detail, HttpStatus.CONFLICT);
    }
}
