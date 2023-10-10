package com.quironlabs.api.config.exceptions;


public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {}

    public UnauthorizedException(Exception e) {
        super(e);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
