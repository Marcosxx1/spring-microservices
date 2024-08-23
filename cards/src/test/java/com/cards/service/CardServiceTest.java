package com.cards.service;

import com.cards.domain.entity.Card;
import com.cards.repository.CardRepository;
import com.cards.service.CardServiceImpl;
import com.cards.utils.CardUtils;
import com.cards.commom.exception.CardAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CardServiceTest {

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private CardUtils cardUtils;

    @MockBean
    private MessageSourceAccessor messageSourceAccessor;

     private CardServiceImpl cardService;

    @Test
    public void testCreateCard_whenCardAlreadyExists_thenThrowCardAlreadyExistsException() {
        String mobileNumber = "987654321";

         when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(new Card()));

         cardService = new CardServiceImpl(cardRepository, cardUtils);

         assertThrows(CardAlreadyExistsException.class, () -> cardService.createCard(mobileNumber));

         verify(cardRepository, never()).save(any(Card.class));
    }
}
