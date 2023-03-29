package com.noirix.repository.impl;

import com.noirix.domain.Car;
import com.noirix.repository.CarRepository;
import com.noirix.repository.rowmapper.CarRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
@Primary
@RequiredArgsConstructor

public class CarRepositoryJdbcTemplateImpl implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final CarRowMapper carRowMapper;

    public Integer getNumberOfCarsByUserId(Long user_id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("findAllCarsForUser");
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_user_id", user_id);
        Integer carsNumber = jdbcCall.executeFunction(Integer.class, in);
        return carsNumber;
    }

    public void changeCarsIsDeletedStatusByUserId(Long user_id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("carsChangeIsDeleted");
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_user_id", user_id);
        jdbcCall.execute(in);
    }

    @Override
    public Car findOne(Long id) {
        String sqlQuery = "select * from cars where id = :carId";
        return namedParameterJdbcTemplate.queryForObject(sqlQuery, new MapSqlParameterSource("carId", id), carRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String sqlQuery = "DELETE from cars where id = :carId";
        namedParameterJdbcTemplate.update(sqlQuery, new MapSqlParameterSource("carId", id));
    }

    @Override
    public List<Car> searchCar(String query, Float price) {
        final String sqlQuery =
                "select * from cars where lower(name) like :query and price > :carPrice order by id desc";
        return namedParameterJdbcTemplate.query(sqlQuery, new MapSqlParameterSource("query",
                '%' + query + '%').addValue("carPrice", price), carRowMapper);
    }

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query("select * from cars order by id desc", carRowMapper);
    }

    public List<Car> findAll(int page, int offset) {
        int totalOffset = (page - 1) * offset;
        return jdbcTemplate.query("select * from cars order by price desc limit " + totalOffset +
                " offset " + offset, carRowMapper);
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
        final String insertNewCarQuery = "INSERT INTO CARS (name, brand, price, user_id, created, changed) VALUES " +
                "('" + carName + "','" + carBrand + "','" + carPrice + "','" + carUserId + "','" + carCreated + "','"
                + carChanged + "')";
        jdbcTemplate.execute(insertNewCarQuery);
    }

    public Long getIdOfLastAddedCar() {
        final String findIdIdOfLastAddedCar = "select MAX(id) from cars";
        return jdbcTemplate.queryForObject(findIdIdOfLastAddedCar, Long.class);
    }

    @Override
    public Car update(Car car) {
        Long carId = car.getId();
        String carName = car.getName();
        String carBrand = car.getBrand();
        Float carPrice = car.getPrice();
        Long carUserId = car.getUser_id();
        Timestamp carCreated = car.getCreated();
        Timestamp carChanged = car.getChanged();
        final String sqlString = "UPDATE cars SET name = '" + carName + "', brand = '" + carBrand + "', price = '" +
                carPrice + "', user_id = '" + carUserId + "', created = '" + carCreated + "', changed = '" +
                carChanged + "' WHERE id = " + carId;
        jdbcTemplate.update(sqlString);
        return findOne(carId);
    }
}
