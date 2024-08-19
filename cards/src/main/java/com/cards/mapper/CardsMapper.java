package com.cards.mapper;

import com.cards.domain.dto.CardsDto;
import com.cards.domain.entity.Card;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Card card) {
        return CardsDto.builder()
                .mobileNumber(card.getMobileNumber())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }

    public static Card mapToCard(CardsDto cardsDto, Card card) {
        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setCardType(cardsDto.getCardType());
        card.setTotalLimit(cardsDto.getTotalLimit());
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setAvailableAmount(cardsDto.getAvailableAmount());
        return card;
    }
}
