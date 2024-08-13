package com.accounts.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@Schema(name = "Customer", description = "Schema that holds Cusomer and Account information")
public class PostNewCustomerRequest {

    @Schema(description = "Customer's name", example = "Maria")
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, max = 30, message = "name's length must be between 5 and 30 characters")
    private String name;

    @Schema(description = "Customer's e-mail", example = "maria@email.com")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "invalid email")
    private String email;

    @Schema(description = "Customer's mobile number", example = "12345678901")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "mobileNumber must be 11 digits")
    private String mobileNumber;

    @Schema(description = "Customer's account details")
    private AccountsDto accountsDto;
}
