package com.noirix.controller.mvc;

import com.noirix.controller.requests.CarCreateRequest;
import com.noirix.controller.requests.CarSearchCriteria;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.Car;
import com.noirix.domain.User;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/cars")
@RequiredArgsConstructor

public class CarRestController {

    private static final Logger log = Logger.getLogger(CarRestController.class);
    private final CarService carService;

    @GetMapping
    public ResponseEntity<Object> getAllCars() {
        List<Car> cars = carService.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
        //throw new RuntimeException("Huston, we have a problem here!");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findOneById(@PathVariable String id) {
        Long parsedUserId;
        try {
            parsedUserId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Car id: " + id + " cannot be parsed to Long", e);
            parsedUserId = 1L;
        }
        Car car = carService.findOne(parsedUserId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> searchCarByParam(@ModelAttribute CarSearchCriteria criteria) {
        Float parsedPrice;
        try {
            parsedPrice = Float.parseFloat(criteria.getPrice());
        } catch (NumberFormatException e) {
            log.error("Car param price: " + criteria.getPrice() + " cannot be parsed to Float", e);
            parsedPrice = 100_000f;
        }
        List<Car> searchList = carService.searchCar(criteria.getQuery(), parsedPrice);
        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }
}