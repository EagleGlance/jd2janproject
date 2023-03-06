package com.noirix.service;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    @Override
    public Car findOne(Long id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> searchMostExpensiveCars() {
        return carRepository.searchMostExpensiveCars();
    }

    @Override
    public void addNewCar(Car car) {
        carRepository.addNewCar(car);
    }

    @Override
    public void addNewCarIfItsPriceHighest(Car car) {
        carRepository.addNewCarIfItsPriceHighest(car);
    }

    @Override
    public void deleteCarWithMaxPrice() {
        carRepository.deleteCarWithMaxPrice();
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
}
