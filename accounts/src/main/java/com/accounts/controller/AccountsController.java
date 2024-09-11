package com.accounts.controller;

import com.accounts.domain.dto.*;
import com.accounts.domain.dto.CustomerResponse;
import com.accounts.domain.dto.ErrorResponseDto;
import com.accounts.domain.dto.PostNewCustomerRequest;
import com.accounts.domain.dto.ResponseDto;
import com.accounts.domain.entity.Accounts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "CRUD for accounts", description = "Create, Update, fetch and Delete account details")
@RequestMapping(
        value = "/api",
        produces = {"application/json"})
public interface AccountsController {

    @Operation(
            summary = "Create account",
            description =
                    "POST method to create a new Account. Returns 201 if successful, 409 if the customer already exists.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "HTTP status Created. Account successfully created.",
                content = @Content(schema = @Schema(implementation = ResponseDto.class))),
        @ApiResponse(
                responseCode = "409",
                description = "HTTP status Conflict. Customer already exists.",
                content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
        @ApiResponse(
                responseCode = "400",
                description = "HTTP status Bad Request. Invalid request parameters.",
                content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody PostNewCustomerRequest postNewCustomerRequest);

    @Operation(summary = "Fetch account", description = "GET method to fetch an Account")
    @ApiResponse(responseCode = "201", description = "HTTP status code created")
    @GetMapping("/fetch")
    ResponseEntity<CustomerResponse> fetchAccountDetails(
            @Pattern(regexp = "^$|[0-9]{11}", message = "mobileNumber must be 11 digits") @RequestParam
                    String mobileNumber);

    @Operation(summary = "Fetch all accounts", description = "GET method to fetch all Accounts")
    @ApiResponse(responseCode = "201", description = "HTTP status OK")
    @GetMapping("/fetch-all")
    ResponseEntity<List<Accounts>> fetchAllAcounts();

    @Operation(summary = "Update an account", description = "PUT method to update an Account")
    @ApiResponse(responseCode = "200", description = "HTTP status OK")
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerResponse customerResponse);

    @Operation(summary = "Delete an account", description = "DELETE method to delete an Account")
    @ApiResponse(responseCode = "200", description = "HTTP status OK")
    @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error")
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "^$|[0-9]{11}", message = "mobileNumber must be 11 digits") @RequestParam
                    String mobileNumber);

    @Operation(summary = "Get build info", description = "Fetch service info")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP STATUS Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/build-info") // Using @Value
    ResponseEntity<String> getBuildInfo();

    @GetMapping("/java-version") // Using Environment environment
    ResponseEntity<String> getJavaVersion();

    @GetMapping("/contact-info") // Using AccountContactInfo
    ResponseEntity<AccountContactInfo> getContactInfo();
}
