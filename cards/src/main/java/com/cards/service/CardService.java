package com.cards.service;

import com.cards.domain.dto.CardsDto;

public interface CardService {

    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber); // TODO - ...Response

    boolean updateCard(CardsDto cardsDto); // TODO - ...Request

    boolean deleteCard(String mobileNumber);
}
