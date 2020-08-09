package com.example.ecom.exception.common;


public class FieldValidationException extends RuntimeException {

    public FieldValidationException(final String message) {
        super(message);
    }
}
