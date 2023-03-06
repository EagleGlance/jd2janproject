package com.noirix.repository;

import com.noirix.domain.Car;

import java.util.List;

public interface CarRepository extends CRUDRepository <Long, Car> {
    List<Car> searchMostExpensiveCars();
    void addNewCar(Car car);
    void addNewCarIfItsPriceHighest(Car car);
    void deleteCarWithMaxPrice();
}
