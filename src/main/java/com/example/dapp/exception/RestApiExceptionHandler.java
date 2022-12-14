package com.example.dapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NonUniqueResultException;

@RestControllerAdvice
public class RestApiExceptionHandler {
    @ExceptionHandler(value = { IllegalArgumentException.class})
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();

        ErrorCode errorCode = ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION;

        restApiException.setHttpStatus(errorCode.getHttpStatus());
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(restApiException, restApiException.getHttpStatus());
    }

    @ExceptionHandler(value = { NullPointerException.class})
    public ResponseEntity<Object> handleApiRequestException(NullPointerException ex) {
        RestApiException restApiException = new RestApiException();

        ErrorCode errorCode = ErrorCode.NULL_POINTER_EXCEPTION;

        restApiException.setHttpStatus(errorCode.getHttpStatus());
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(restApiException, restApiException.getHttpStatus());
    }

    @ExceptionHandler(value = { NonUniqueResultException.class})
    public ResponseEntity<Object> handleApiRequestException(NonUniqueResultException ex) {
        RestApiException restApiException = new RestApiException();

        ErrorCode errorCode = ErrorCode.NON_UNIQUE_RESULT_EXCEPTION;

        restApiException.setHttpStatus(errorCode.getHttpStatus());
        restApiException.setErrorMessage(ex.getMessage());

        return new ResponseEntity(restApiException, restApiException.getHttpStatus());
    }
}
