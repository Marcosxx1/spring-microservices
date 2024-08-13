package com.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema that holds successful responses information")
public class ResponseDto {

    @Schema(description = "Status code in the response", example = "200")
    private HttpStatus statusCode;

    @Schema(description = "Status message in the response", example = "Request processed successfully")
    private String statusMsg;
}
