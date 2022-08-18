package com.example.dapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.persistence.NonUniqueResultException;

@Getter
public enum ErrorCode {
    ILLEGAL_ARGUMENT_EXCEPTION(HttpStatus.BAD_REQUEST, "400_1"),

    NULL_POINTER_EXCEPTION(HttpStatus.BAD_REQUEST, "400_2"),

    NON_UNIQUE_RESULT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "500_1");

    private final HttpStatus httpStatus;
    private final String errorCode;

    ErrorCode(HttpStatus httpStatus, String errorCode) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
