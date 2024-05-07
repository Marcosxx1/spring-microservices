package com.eazybytes.accounts.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data// @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class AccountsDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
