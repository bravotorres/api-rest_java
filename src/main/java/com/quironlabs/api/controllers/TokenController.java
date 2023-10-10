package com.quironlabs.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quironlabs.api.services.JwtService;


@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private JwtService service;
    
    @GetMapping
    public ResponseEntity<?> getToken(@RequestHeader("Authorization") String authorization) {
        return service.getAccessToken(authorization);
    }
}
