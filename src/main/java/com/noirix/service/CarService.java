package com.noirix.service;

import com.noirix.domain.Car;

import java.util.List;

public interface CarService {
    Car findOne(Long id);

    List<Car> findAll();

    List<Car> findAll(int page, int offset);

    Car create(Car object);

    Car update(Car object);

    void delete(Long id);

    List<Car> searchCar(String query, Float price);

    Integer getNumberOfCarsByUserId(Long parsedUserId);
}

