package com.cards.commom.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema that holds error response information")
public class ErrorResponse extends RuntimeException {

    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the error that happened")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error that happened")
    private String errorMessage;
}
