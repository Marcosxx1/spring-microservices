package com.loans.commom.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ErrorResponseCustom extends RuntimeException {

    private final String title;
    private final String detail;
    private final HttpStatus httpStatus;
    private final LocalDateTime errorTime;

    public ErrorResponseCustom(String title, String detail, HttpStatus httpStatus, LocalDateTime errorTime) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.errorTime = errorTime;
    }
}
