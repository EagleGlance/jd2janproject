package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateLocation;
import com.noirix.repository.hibernate.HibernateLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/locations")
@RequiredArgsConstructor
public class LocationController {

    private final HibernateLocationRepository locationRepository;

    @GetMapping
    public ResponseEntity<Object> getAllLocations() {
        List<HibernateLocation> locations = locationRepository.findAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}
