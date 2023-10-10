package com.quironlabs.api.config.exceptions;


public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {}

    public ForbiddenException(Exception e) {
        super(e);
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
