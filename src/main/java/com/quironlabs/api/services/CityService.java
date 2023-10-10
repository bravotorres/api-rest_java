package com.quironlabs.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quironlabs.api.config.responses.ResponseFactory;
import com.quironlabs.api.models.entities.requests.CityRequest;


@Service
public class CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private ResponseFactory responseFactory;

    public ResponseEntity<?> createCity(CityRequest city) {
        return responseFactory.getResponse201("Ok!");
    }

    public ResponseEntity<?> readCity(Integer id) {
        logger.info("ID={}", id);
        if (id != null) {
            return responseFactory.getResponse200("read City.id=" + id + " - Ok!");
        } else {
            return responseFactory.getResponse200("read All Cities - Ok!");
        }
    }

    public ResponseEntity<?> updateCity(Integer id, CityRequest city) {
        return responseFactory.getResponse200("Ok!");
    }

    public ResponseEntity<?> deleteCity(Integer id) {
        return responseFactory.getResponse200("Ok!");
    }
}
