package com.loans.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Schema(name = "Response", description = "Schema to hold successful response information")
@Data
@AllArgsConstructor
public class Response {

    @Schema(description = "Status code in the response")
    private HttpStatus statusCode;

    @Schema(description = "Status message in the response")
    private String statusMsg;
}
