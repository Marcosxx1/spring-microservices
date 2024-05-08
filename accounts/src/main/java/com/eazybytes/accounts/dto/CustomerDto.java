package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data// @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, max = 30, message = "name's length must be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{11})", message = "mobileNumber must be 11 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
