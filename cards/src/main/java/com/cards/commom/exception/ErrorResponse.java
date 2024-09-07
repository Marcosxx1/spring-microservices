package com.cards.commom.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema that holds error response information")
public class ErrorResponse {

    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the error that happened")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error that happened")
    private String errorMessage;

}
