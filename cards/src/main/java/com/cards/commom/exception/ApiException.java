package com.cards.commom.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {

    private final String title;
    private final String detail;
    private final HttpStatus httpStatus;

    public ApiException(String title, String detail, HttpStatus httpStatus) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
}
