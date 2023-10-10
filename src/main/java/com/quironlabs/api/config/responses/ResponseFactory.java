package com.quironlabs.api.config.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    public ResponseEntity<?> getResponse200(Object result) {
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    public ResponseEntity<?> getResponse201(Object result) {
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
