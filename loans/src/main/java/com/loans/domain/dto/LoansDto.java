package com.loans.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(name = "Loans", description = "Schema to hold Loan information")
@Data
@Builder
public class LoansDto {

    @NotEmpty(message = "{validation.mobileNumber.notEmpty}")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "{validation.mobileNumber.pattern}")
    @Schema(description = "Mobile Number of Customer", example = "4365327698")
    private String mobileNumber;

    @NotEmpty(message = "{validation.loanNumber.notEmpty}")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "{validation.loanNumber.pattern}")
    @Schema(description = "Loan Number of the customer", example = "548732457654")
    private String loanNumber;

    @NotEmpty(message = "{validation.loanType.notEmpty}")
    @Schema(description = "Type of the loan", example = "Home Loan")
    private String loanType;

    @Positive(message = "{validation.totalLoan.positive}")
    @Schema(description = "Total loan amount", example = "100000")
    private int totalLoan;

    @PositiveOrZero(message = "{validation.amountPaid.positiveOrZero}")
    @Schema(description = "Total loan amount paid", example = "1000")
    private int amountPaid;

    @PositiveOrZero(message = "{validation.outstandingAmount.positiveOrZero}")
    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    private int outstandingAmount;
}
