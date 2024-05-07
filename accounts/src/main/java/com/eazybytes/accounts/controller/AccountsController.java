package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor// line 21, with this annotation, we don't need the injection or @Autowired because it will do it for us
public class AccountsController {

    private IAccountsService iAccountsService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
}
