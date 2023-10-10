package com.quironlabs.api.controllers;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quironlabs.api.models.entities.requests.CityRequest;
import com.quironlabs.api.services.CityService;


@RestController
@RequestMapping("/v1/cities")
public class CitiesController {
    // private static final Logger logger = LoggerFactory.getLogger(CitiesController.class);

    @Autowired
    private CityService service;

    // Create a new record of City
    @PostMapping
    public ResponseEntity<?> postCity(@RequestBody CityRequest city) {
        return service.createCity(city);
    }

    @GetMapping({"", "/{id}"})
    public ResponseEntity<?> getCities(@PathVariable(required = false) Integer id) {
        return service.readCity(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody CityRequest city) {
        return service.updateCity(id, city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        return service.deleteCity(id);
    }
}
