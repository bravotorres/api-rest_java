package com.quironlabs.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quironlabs.api.config.responses.ResponseFactory;


@RestController
public class StatusController {
    private static final Logger logger = LoggerFactory.getLogger(StatusController.class);
    @Autowired
    private ResponseFactory responseFactory;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/status")
    public ResponseEntity<?> getStatus() {
        String appName = env.getProperty("spring.application.name");
        String environ = env.getProperty("ENVIRONMENT");
        
        // Additional Data
        Map<String, Object> options = new HashMap<>();
        
         options.put("port", env.getProperty("server.port", ""));
         options.put("app_name", appName);
         options.put("app_version", env.getProperty("app.version", ""));
         options.put("env", environ);
        // options.put("build", env.getProperty("BUILD_NUMBER", ""));
        // options.put("buildId", env.getProperty("BUILD_ID", ""));
        
        // StatusResponse response = new StatusResponse(appName, environ, options);
        logger.info("app={}, env={}", appName, environ);
        // logger.info("Consulting status service: {}", response);
        
        return responseFactory.getResponse200(options);
    }
}

