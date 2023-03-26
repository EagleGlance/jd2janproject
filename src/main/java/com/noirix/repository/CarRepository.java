package com.noirix.repository;

import com.noirix.domain.Car;
import java.util.List;

public interface CarRepository extends CRUDRepository<Long, Car> {
    List<Car> searchCar(String query, Float price);

    List<Car> findAll(int page, int offset);
}

