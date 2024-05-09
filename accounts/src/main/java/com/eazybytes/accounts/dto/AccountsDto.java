package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data// @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class AccountsDto {

    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "accountNumber must be 11 digits")
    @Schema(description = "Account number. Must be 11 digits.", required = true, example = "12345678901")
    private Long accountNumber;

    @NotEmpty(message = "accountType cannot be empty")
    private String accountType;

    @NotEmpty(message = "branchAddress cannot be empty")
    private String branchAddress;
}
