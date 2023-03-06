package com.noirix.service;

import com.noirix.domain.Car;
import com.noirix.repository.CRUDRepository;

import java.util.List;

public interface CarService extends CRUDRepository<Long, Car> {
    List<Car> searchMostExpensiveCars();
    void addNewCar(Car car);
    void addNewCarIfItsPriceHighest(Car car);
    void deleteCarWithMaxPrice();
}
