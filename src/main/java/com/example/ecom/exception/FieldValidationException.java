package com.example.ecom.exception;


public class FieldValidationException extends RuntimeException {

    public FieldValidationException(final String message) {
        super(message);
    }
}
