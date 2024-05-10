package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD for accounts",
        description = "Create, Update, fetch and Delete account details"
)
public class AccountsController {

    private IAccountsService iAccountsService;


    @Operation(
            summary = "Creat account",
            description = "POST method to create a new Account"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }



    @Operation(
            summary = "Fetch account",
            description = "GET method to fetch an Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status code created"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @Pattern(regexp = "^$|[0-9]{11}", message = "mobileNumber must be 11 digits")
            @RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Fetch all accounts",
            description = "GET method to fetch all Accounts"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch-all")
    public ResponseEntity<List<Accounts>> fetchAllAcounts() {
        List<Accounts> accounts = iAccountsService.fetchAll();

        if (accounts.size() > 0) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accounts);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    @Operation(
            summary = "Update an account",
            description = "PUT method to update an Account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Internal Server Error",
            content = @Content(
                    schema = @Schema( implementation = ErrorResponse.class)
            )
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean updated = iAccountsService.updateAccount(customerDto);
        if (updated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(AccountConstants.STATUS_404, AccountConstants.MESSAGE_404));
        }
    }

    @Operation(
            summary = "Delete an account",
            description = "DELETE method to delete an Account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @ApiResponse(
            responseCode = "500",
            description = "HTTP Internal Server Error"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "^$|[0-9]{11}", message = "mobileNumber must be 11 digits")
            @RequestParam String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_204, AccountConstants.MESSAGE_204));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("400", "something went wrong"));
        }
    }
}
