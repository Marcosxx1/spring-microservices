package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data// @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@Schema(
        name = "Accounts",
        description = "Schema that holds Account information"
)
public class AccountsDto {


    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{10})", message = "accountNumber must be 11 digits")
    @Schema(description = "Account's number", required = true, example = "12345678901")
    private Long accountNumber;


    @NotEmpty(message = "accountType cannot be empty")
    @Schema(description = "Account's type", required = true, example = "Savings")
    private String accountType;

    @NotEmpty(message = "branchAddress cannot be empty")
    @Schema(description = "Address", required = true, example = "Any Avenue, 1st")
    private String branchAddress;
}
