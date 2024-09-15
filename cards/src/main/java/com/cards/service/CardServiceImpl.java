package com.cards.service;

import com.cards.commom.exception.ExceptionMessageUtils;
import com.cards.domain.dto.CardsDto;
import com.cards.domain.entity.Card;
import com.cards.mapper.CardsMapper;
import com.cards.repository.CardRepository;
import com.cards.utils.CardUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardUtils cardUtils;

    @Override
    public void createCard(String mobileNumber) {
        cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> ExceptionMessageUtils.cardAlreadyExistsException(mobileNumber));
        cardRepository.save(cardUtils.returnNewCard(mobileNumber));
    }

    @Override
    public Card fetchCard(String mobileNumber) {
        return cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("Card", mobileNumber));
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Card card = fetchCard(cardsDto.getMobileNumber());
        CardsMapper.mapToCard(cardsDto, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = fetchCard(mobileNumber);
        cardRepository.deleteById(card.getId());
        return true;
    }
}
