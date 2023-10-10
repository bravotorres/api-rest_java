package com.quironlabs.api.config.exceptions;


public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public NotFoundException() {}
    
    public NotFoundException(Exception e) {
        super(e);
    }
    
    public NotFoundException(String message) {
        super(message);
    }
}
