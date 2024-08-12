package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.PostNewCustomerRequest;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "CRUD for accounts", description = "Create, Update, fetch and Delete account details")
@RequestMapping(
        value = "/api",
        produces = {"application/json"})
public interface AccountsController {

    @Operation(summary = "Create account", description = "POST method to create a new Account")
    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody PostNewCustomerRequest postNewCustomerRequest);

    @Operation(summary = "Fetch account", description = "GET method to fetch an Account")
    @ApiResponse(responseCode = "201", description = "HTTP status code created")
    @GetMapping("/fetch")
    ResponseEntity<PostNewCustomerRequest> fetchAccountDetails(
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
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody PostNewCustomerRequest postNewCustomerRequest);

    @Operation(summary = "Delete an account", description = "DELETE method to delete an Account")
    @ApiResponse(responseCode = "200", description = "HTTP status OK")
    @ApiResponse(responseCode = "500", description = "HTTP Internal Server Error")
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "^$|[0-9]{11}", message = "mobileNumber must be 11 digits") @RequestParam
                    String mobileNumber);
}
