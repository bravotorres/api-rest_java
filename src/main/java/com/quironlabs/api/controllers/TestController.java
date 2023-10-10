package com.quironlabs.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    @GetMapping
    public ResponseEntity<?> test() {
        try {
            Map<String, Object> response = new HashMap<>();
            
            String pass = "esteganografia01+";
            response.put("string", pass);
            
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String passCoded = encoder.encode(pass);
            response.put("string", passCoded);
            
            logger.info("pass({}, {})", pass, passCoded);
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error in test: {}", e.getMessage());

            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
