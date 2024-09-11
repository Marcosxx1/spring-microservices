package com.loans.controller;

import com.loans.domain.dto.LoanContactInfo;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Loans", description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE loan details")
@RequestMapping(
        path = "/api",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public interface LoansController {

    @Operation(summary = "Create Loan REST API", description = "REST API to create new loan")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/create")
    ResponseEntity<Response> createLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/fetch")
    ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/update")
    ResponseEntity<Response> updateLoanDetails(@Valid @RequestBody LoansDto loansDto);

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/delete")
    ResponseEntity<Response> deleteLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @GetMapping("/build-info") // Using @Value
    ResponseEntity<String> getBuildInfo();

    @GetMapping("/java-version") // Using Environment environment
    ResponseEntity<String> getJavaVersion();

    @GetMapping("/contact-info") // Using AccountContactInfo
    ResponseEntity<LoanContactInfo> getContactInfo();
}
