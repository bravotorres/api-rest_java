package com.quironlabs.api.config.exceptions;


public class InternalServerError extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public InternalServerError() {}
    
    public InternalServerError(Exception e) {
        super(e);
    }
    
    public InternalServerError(String message) {
        super(message);
    }
}
