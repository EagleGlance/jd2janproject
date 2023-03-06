package com.noirix.repository;

import com.noirix.configuration.DatabaseProperties;
import com.noirix.domain.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CarRepositoryEmptyImpl implements CarRepository {
    private final DatabaseProperties properties;
    @Override
    public Car findOne(Long id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car create(Car object) {
        return null;
    }

    @Override
    public Car update(Car object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Car> searchMostExpensiveCars() {
        return null;
    }

    @Override
    public void addNewCar(Car car) {

    }

    @Override
    public void addNewCarIfItsPriceHighest(Car car) {

    }

    @Override
    public void deleteCarWithMaxPrice() {

    }
}
