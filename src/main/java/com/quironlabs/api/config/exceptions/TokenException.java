package com.quironlabs.api.config.exceptions;


public class TokenException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public TokenException() {}

    public TokenException(Exception e) {
        super(e);
    }

    public TokenException(String message) {
        super(message);
    }
}
