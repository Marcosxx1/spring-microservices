package com.loans.commom.exception.handler;

import static com.loans.commom.exception.CommonErrorTypes.ERROR;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ErrorResponse {
    private String path;
    private String title;
    private String detail;

    public ErrorResponse(String title, String detail) {
        this.path = ERROR;
        this.title = title;
        this.detail = detail;
    }
}
