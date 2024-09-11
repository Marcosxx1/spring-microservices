package com.cards.controller;

import com.cards.domain.dto.CardContactInfo;
import com.cards.domain.dto.CardsDto;
import com.cards.domain.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CardsController {

    @Operation(summary = "Create Card REST API", description = "REST API to create new Card")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
        @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @PostMapping("/create")
    ResponseEntity<Response> createCard(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @GetMapping("/fetch")
    ResponseEntity<CardsDto> fetchCardDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @PutMapping("/update")
    ResponseEntity<Response> updateCardDetails(@Valid @RequestBody CardsDto cardsDto);

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
        @ApiResponse(responseCode = "417", description = "Expectation Failed"),
        @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error")
    })
    @DeleteMapping("/delete")
    ResponseEntity<Response> deleteCardDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                    String mobileNumber);

    @GetMapping("/build-info") // Using @Value
    ResponseEntity<String> getBuildInfo();

    @GetMapping("/java-version") // Using Environment environment
    ResponseEntity<String> getJavaVersion();

    @GetMapping("/contact-info") // Using AccountContactInfo
    ResponseEntity<CardContactInfo> getContactInfo();
}
