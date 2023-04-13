package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateCars;
import com.noirix.repository.hibernate.HibernateCarsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/cars")
@RequiredArgsConstructor
public class CarsController {

    private final HibernateCarsRepository carsRepository;

    @GetMapping
    public ResponseEntity<Object> getAllCars() {
        List<HibernateCars> cars = carsRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
