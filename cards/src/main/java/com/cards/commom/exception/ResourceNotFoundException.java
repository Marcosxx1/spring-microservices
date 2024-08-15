package com.cards.commom.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super();
    }
}
