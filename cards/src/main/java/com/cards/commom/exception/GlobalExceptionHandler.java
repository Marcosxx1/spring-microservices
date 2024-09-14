package com.cards.commom.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // if any exception happen, it will be caught here
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseCustom> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest webRequest) {

        ErrorResponseCustom errorResponseDto =
                new ErrorResponseCustom(webRequest.getDescription(false), ex.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseCustom> handleCardAlreadyExistsException(
            CardAlreadyExistsException ex, WebRequest webRequest) {

        ErrorResponseCustom errorResponseDto =
                new ErrorResponseCustom(webRequest.getDescription(false), ex.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseCustom> handleGlobalException(Exception ex, WebRequest webRequest) {

        ErrorResponseCustom errorResponseDto = new ErrorResponseCustom(
                webRequest.getDescription(false), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
