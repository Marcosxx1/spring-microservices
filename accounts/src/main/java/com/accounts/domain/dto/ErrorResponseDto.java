package com.accounts.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema that holds error response information")
public class ErrorResponseDto {
    /*Here that de can define all the error messages?*/
    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the error that happened")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error that happened")
    private String errorMessage;

    @Schema(description = "Time that the error happened")
    private LocalDateTime errorTime;
}
