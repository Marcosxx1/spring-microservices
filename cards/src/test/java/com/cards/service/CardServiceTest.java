package com.cards.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.cards.commom.exception.CardAlreadyExistsException;
import com.cards.commom.exception.handler.ExceptionMessageUtils;
import com.cards.constants.CardsConstants;
import com.cards.domain.entity.Card;
import com.cards.repository.CardRepository;
import com.cards.utils.CardUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CardServiceImpl.class, CardUtils.class})
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private MessageSourceAccessor messageSourceAccessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageSourceAccessor = mock(MessageSourceAccessor.class);
        ReflectionTestUtils.setField(ExceptionMessageUtils.class, "staticMessageSourceAccessor", messageSourceAccessor);
    }

    @Test
    public void testCreateCard_whenCardAlreadyExists_thenThrowCardAlreadyExistsException() {
        String mobileNumber = "987654321";
        String errorMessage = "resource.already.exists";

        when(cardRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(new Card()));
        when(messageSourceAccessor.getMessage(CardsConstants.RESOURCE_ALREADY_EXISTS))
                .thenReturn(errorMessage);

        CardAlreadyExistsException thrown = assertThrows(CardAlreadyExistsException.class, () -> {
            cardService.createCard(mobileNumber);
        });

        assert (thrown.getMessage()).equals(errorMessage);
    }
}
