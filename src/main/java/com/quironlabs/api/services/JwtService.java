package com.quironlabs.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quironlabs.api.models.entities.AuthUser;
import com.quironlabs.api.models.entities.responses.JwtResponse;
import com.quironlabs.api.models.repositories.UserRepository;
import com.quironlabs.api.utils.JwtUtils;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private JwtUtils utils;
    
    public ResponseEntity<?> getAccessToken(String basicAuthorizationHeader) {
        logger.info("Token service: --h 'Authorization:{}'", basicAuthorizationHeader);
        
        AuthUser user = utils.getUserForAuth(basicAuthorizationHeader);
        
//        JwtResponse response = service.login(user);
        JwtResponse response = new JwtResponse(utils.createJwt(user.getUsername()));
        logger.info("TokenResponse: {}", response);
        
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
