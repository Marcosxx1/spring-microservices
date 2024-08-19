package com.cards.utils;

import com.cards.constants.CardsConstants;
import com.cards.domain.entity.Card;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CardUtils {

    private Card createNewCard(String mobileNumber) {
        String randomCardNumber = UUID.randomUUID().toString();

        return Card.builder()
                .cardNumber(randomCardNumber)
                .mobileNumber(mobileNumber)
                .cardType(CardsConstants.CREDIT_CARD)
                .totalLimit(CardsConstants.NEW_CARD_LIMIT)
                .amountUsed(0)
                .availableAmount(CardsConstants.NEW_CARD_LIMIT)
                .build();
    }

    public Card returnNewCard(String mobileNumber) {
        return createNewCard(mobileNumber);
    }
}
