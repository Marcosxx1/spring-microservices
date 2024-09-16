package com.loans.commom.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

 public class LoanAlreadyExistsException extends ErrorResponseCustom {

    public LoanAlreadyExistsException(String title, String detail, LocalDateTime errorTime) {
        super(title, detail,HttpStatus.CONFLICT, errorTime );
    }
}
