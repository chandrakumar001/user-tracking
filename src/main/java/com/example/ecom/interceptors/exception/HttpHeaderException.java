package com.example.ecom.interceptors.exception;

public class HttpHeaderException extends RuntimeException {

    public HttpHeaderException(final String message) {
        super(message);
    }
}
