package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.repository.rowmapper.CarRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<Car> searchCar(String query, Float price) {
        return null;
    }
}
