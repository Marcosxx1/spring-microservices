package com.cards.service;

import com.cards.domain.dto.CardsDto;
import com.cards.domain.entity.Card;

public interface CardService {

    void createCard(String mobileNumber);

    Card fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto); // TODO - ...Request

    boolean deleteCard(String mobileNumber);
}
