package com.noirix.controller.mvc;

import com.noirix.controller.requests.CarCreateRequest;
import com.noirix.controller.requests.CarSearchCriteria;
import com.noirix.controller.requests.PageCriteria;
import com.noirix.domain.Car;
import com.noirix.service.CarService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    }

    @GetMapping(value = "/paging")
    public ResponseEntity<Object> getAllCarsByPriceInPages(@ModelAttribute PageCriteria criteria) {
        List<Car> cars = carService.findAll(criteria.getPage(), criteria.getOffset());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findOneById(@PathVariable String id) {
        Long parsedCarId = Long.parseLong(id);
        Car car = carService.findOne(parsedCarId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     * Method execute function in DB with body:
     * create or replace function findAllCarsForUser (in_user_id bigint)
     * returns integer
     * language plpgsql
     * as
     *     $$
     *     declare carsNumber integer;
     * BEGIN
     *     select count(*) into carsNumber from cars
     *         where user_id = in_user_id;
     *     return carsNumber;
     * end;
     * $$
     *
     * @param user_id - is "id" of user we calculate the number of cars in DB for
     * @return number of cars from DB for the user with "id"
     */

    @GetMapping(value = "/calculate/{user_id}")
    public ResponseEntity<Object> calculateNumberOfCarsByUserId(@PathVariable String user_id) {
        Long parsedUserId = Long.parseLong(user_id);
        Integer numberOfCars = carService.getNumberOfCarsByUserId(parsedUserId);
        return new ResponseEntity<>(numberOfCars, HttpStatus.OK);
    }

    /**
     * Method execute procedure in DB with body:
     * create or replace procedure carsChangeIsDeleted (IN in_user_id bigint)
     *     language plpgsql
     * as $$
     * BEGIN
     *     update cars
     *     set is_deleted = true
     *     where user_id = in_user_id;
     *     commit;
     * end;
     * $$;
     *
     * @param user_id is "id" of user we change the status "is_deleted" of cars in DB  to true
     * @return only status 200.OK
     */

    @PatchMapping(value = "/isDeleted/{user_id}")
    public ResponseEntity<Object> changeCarsIsDeletedStatusByUserId(@PathVariable String user_id) {
        Long parsedUserId = Long.parseLong(user_id);
        carService.getNumberOfCarsByUserId(parsedUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * deleting of car in this GET methode is not safety and is just for example but not for use
     * @param id - is "id" of being deleted car from cars table
     * @return in success deleted car as json object with information from all columns from car table
     * with parameter "is_deleted" set true
     */
    @GetMapping(value = "delete/{id}")
    public ResponseEntity<Object> doSomethingWithOneById(@PathVariable String id) {
        Long parsedCarId = Long.parseLong(id);
        Car car = carService.findOne(parsedCarId);
        carService.delete(parsedCarId);
        car.setIs_deleted(true);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Object> searchCarByParam(@ModelAttribute CarSearchCriteria criteria) {
        Float parsedPrice = Float.parseFloat(criteria.getPrice());
        List<Car> searchList = carService.searchCar(criteria.getQuery(), parsedPrice);
        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createCar(@RequestBody CarCreateRequest request) {
        Car build = Car.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .user_id(request.getUser_id())
                .created(request.getCreated())
                .changed(request.getChanged())
                .build();
        Car createdCar = carService.create(build);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOneById(@PathVariable String id) {
        Long parsedCarId = Long.parseLong(id);
        Car car = carService.findOne(parsedCarId);
        carService.delete(parsedCarId);
        car.setIs_deleted(true);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable String id,
                                         @RequestBody CarCreateRequest request) {
        Long parsedCarId = Long.parseLong(id);
        Car build = Car.builder()
                .id(parsedCarId)
                .name(request.getName())
                .brand(request.getBrand())
                .price(request.getPrice())
                .user_id(request.getUser_id())
                .created(request.getCreated())
                .changed(request.getChanged())
                .build();
        Car updatedCar = carService.update(build);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updatePartlyCar(@PathVariable String id,
                                            @RequestBody CarCreateRequest request) {
        Long parsedCarId = Long.parseLong(id);
        Car build = carService.findOne(parsedCarId);
        if (request.getName() != null) build.setName(request.getName());
        if (request.getBrand() != null) build.setBrand(request.getBrand());
        if (request.getPrice() != null) build.setPrice(request.getPrice());
        if (request.getUser_id() != null) build.setUser_id(request.getUser_id());
        if (request.getCreated() != null) build.setCreated(request.getCreated());
        if (request.getChanged() != null) build.setChanged(request.getChanged());
        Car updatedCar = carService.update(build);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
}
