package com.roman_dri.rest_service.error.exception;

public class AccessException extends RuntimeException {

    public AccessException(String message) {
        super(message);
    }
}