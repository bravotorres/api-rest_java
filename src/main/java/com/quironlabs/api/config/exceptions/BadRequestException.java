package com.quironlabs.api.config.exceptions;


public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException() {}
    
    public BadRequestException(Exception e) {
        super(e);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
