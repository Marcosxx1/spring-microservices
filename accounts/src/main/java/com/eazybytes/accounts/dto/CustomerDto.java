package com.eazybytes.accounts.dto;

import lombok.Data;

@Data// @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;

    private AccountsDto accountsDto;
}
