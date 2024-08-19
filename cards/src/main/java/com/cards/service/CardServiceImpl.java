package com.cards.service;

import com.cards.commom.exception.ExceptionMessageUtils;
import com.cards.domain.dto.CardsDto;
import com.cards.domain.entity.Card;
import com.cards.mapper.CardsMapper;
import com.cards.repository.CardRepository;
import com.cards.utils.CardUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardUtils cardUtils;

    @Override
    public void createCard(String mobileNumber) {
        boolean cardExists = cardRepository.findByMobileNumber(mobileNumber).isPresent();
        if (cardExists) {
            throw ExceptionMessageUtils.cardAlreadyExistsException();
        }
        cardRepository.save(cardUtils.returnNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Card card = findOrThrow(cardRepository.findByMobileNumber(mobileNumber), "Card", "Mobile Number", mobileNumber);
        return CardsMapper.mapToCardsDto(card);
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        /* NOTE - Reference Passing: In Java, when we pass an object to a method, we pass the reference to
        *  that object. This means the method operates on the same object in memory. The mapToCard
        *  method updates the fields of the card object directly.

        - No New Object Creation: We're not creating a new Card object inside mapToCard. Instead,
        *  we're modifying the fields of the existing card object. The save operation in
        *  cardRepository.save(card); saves the updated card object.*/

        Card card = findOrThrow(
                cardRepository.findByMobileNumber(cardsDto.getMobileNumber()),
                "Card",
                "Mobile Number",
                cardsDto.getMobileNumber());
        CardsMapper.mapToCard(cardsDto, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = findOrThrow(cardRepository.findByMobileNumber(mobileNumber), "Card", "Mobile Number", mobileNumber);
        cardRepository.deleteById(card.getId());
        return true;
    }

    private <T> T findOrThrow(Optional<T> optional, String entityName, String fieldName, String fieldValue) {
        return optional.orElseThrow(
                () -> ExceptionMessageUtils.resourceNotFoundException(entityName, fieldName, fieldValue));
    }
}
