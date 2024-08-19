package com.cards.controller;

import com.cards.constants.CardsConstants;
import com.cards.domain.dto.CardsDto;
import com.cards.domain.dto.Response;
import com.cards.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Cards", description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE card details")
@RestController
@RequestMapping(
        path = "/api",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CardsControllerImpl implements CardsController {
    private final CardService cardService;
    private final MessageSourceAccessor staticMessageSourceAccessor;

    @Override
    public ResponseEntity<Response> createCard(String mobileNumber) {
        cardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(
                        HttpStatus.CREATED, staticMessageSourceAccessor.getMessage(CardsConstants.MESSAGE_201)));
    }

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        CardsDto cardsDto = cardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Override
    public ResponseEntity<Response> updateCardDetails(@Valid CardsDto cardsDto) {
        boolean isUpdated = cardService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(CardsConstants.MESSAGE_200)));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(
                            HttpStatus.EXPECTATION_FAILED,
                            staticMessageSourceAccessor.getMessage(CardsConstants.MESSAGE_417_UPDATE)));
        }
    }

    @Override
    public ResponseEntity<Response> deleteCardDetails(String mobileNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(
                            HttpStatus.OK, staticMessageSourceAccessor.getMessage(CardsConstants.MESSAGE_200)));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(
                            HttpStatus.EXPECTATION_FAILED,
                            staticMessageSourceAccessor.getMessage(CardsConstants.MESSAGE_417_DELETE)));
        }
    }
}
