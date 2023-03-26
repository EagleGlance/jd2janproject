package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.repository.rowmapper.CarRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor

public class CarRepositoryJdbcTemplateImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarRowMapper carRowMapper;
    @Override
    public Car findOne(Long id) {
        return jdbcTemplate.queryForObject("select * from cars where id = " + id, carRowMapper);
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query("select * from cars order by id desc", carRowMapper);
    }

    @Override
    public Car create(Car object) {
        addNewCar(object);
        Long id = getIdOfLastAddedCar();
        return findOne(id);
    }

    public void addNewCar(Car car) {
        String carName = car.getName();
        String carBrand = car.getBrand();
        Float carPrice = car.getPrice();
        Long carUserId = car.getUser_id();
        Timestamp carCreated = car.getCreated();
        Timestamp carChanged = car.getChanged();
        String insertNewCarQuery = "INSERT INTO CARS (name, brand, price, user_id, created, changed) VALUES " +
                "('" + carName + "','" + carBrand + "','" + carPrice + "','" + carUserId + "','" + carCreated + "','"
                + carChanged + "')";
        jdbcTemplate.execute(insertNewCarQuery);
    }

    public Long getIdOfLastAddedCar() {
        final String findIdIdOfLastAddedCar = "select MAX(id) from cars";
        return jdbcTemplate.queryForObject(findIdIdOfLastAddedCar, Long.class);
    }

    @Override
    public Car update(Car object) {
        return null;
    }

    @Override
    public void delete(Long id) {
        final String sqlQuery = "DELETE from cars where id = " + id ;
        jdbcTemplate.update(sqlQuery);
    }

    @Override
    public List<Car> searchCar(String query, Float price) {
        final String sqlQuery =
                "select * " +
                        " from cars " +
                        " where lower(name) like '%" + query + "%' and " +
                        " price > " + price +
                        " order by id desc";

        return jdbcTemplate.query(sqlQuery, carRowMapper);
    }
}
