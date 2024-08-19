package com.cards.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Schema(name = "Cards", description = "Schema to hold Card information")
@Data
@Builder
public class CardsDto {

    @NotEmpty(message = "{validation.mobileNumber.notEmpty}")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "{validation.mobileNumber.pattern}")
    @Schema(description = "Mobile Number of the customer", example = "4354437687")
    private String mobileNumber;

    @NotEmpty(message = "{validation.cardNumber.notEmpty}")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "{validation.cardNumber.pattern}")
    @Schema(description = "Card Number of the customer", example = "100646930341")
    private String cardNumber;

    @NotEmpty(message = "{validation.cardType.notEmpty}")
    @Schema(description = "Type of the card", example = "Credit Card")
    private String cardType;

    @Positive(message = "{validation.totalLimit.positive}")
    @Schema(description = "Total amount limit available against a card", example = "100000")
    private int totalLimit;

    @PositiveOrZero(message = "{validation.amountUsed.positiveOrZero}")
    @Schema(description = "Total amount used by a customer", example = "1000")
    private int amountUsed;

    @PositiveOrZero(message = "{validation.availableAmount.positiveOrZero}")
    @Schema(description = "Total available amount against a card", example = "90000")
    private int availableAmount;
}
