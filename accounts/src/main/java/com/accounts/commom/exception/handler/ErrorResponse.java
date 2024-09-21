package com.accounts.commom.exception.handler;


import lombok.*;

import static com.accounts.commom.exception.handler.CommonErrorTypes.ERROR;

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
