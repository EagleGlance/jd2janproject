package com.noirix.repository;

import com.noirix.configuration.DatabaseProperties;
import com.noirix.domain.Car;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.noirix.repository.columns.CarColumns.*;
import static com.noirix.repository.columns.CarColumns.IS_DELETED;

@Primary
@Repository
@AllArgsConstructor
public class CarRepositoryImpl implements CarRepository {
    private final DatabaseProperties properties;

    @Override
    public List<Car> findAll() {
        final String findAllQuery = "select * from cars order by price desc";

        List<Car> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Car> searchMostExpensiveCars() {
        List<Car> allCars = findAll();
        Optional<Float> maxPriceOpt = allCars.stream()
                .map(x -> x.getPrice())
                .max((a,b) -> a.compareTo(b));
        Float maxPrice = maxPriceOpt.orElse(0.0f);
        List<Car> mostExpensiveCars = allCars.stream()
                .filter(x -> x.getPrice() >= maxPrice)
                .collect(Collectors.toList());
        return mostExpensiveCars;
    }

    @Override
    public void addNewCar(Car car) {
        String carName = car.getName();
        String carBrand = car.getBrand();
        Float carPrice = car.getPrice();
        Long carUserId = car.getUser_id();
        Timestamp carCreated = car.getCreated();
        Timestamp carChanged = car.getChanged();
        String insertNewCarQuery = "INSERT INTO CARS (name, brand, price, user_id, created, changed) VALUES ('" + carName + "','" + carBrand + "','" + carPrice + "','" + carUserId + "','" + carCreated + "','" + carChanged + "')";
        registerDriver();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertNewCarQuery);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public void deleteCarWithMaxPrice() {
        String deleteCarWithMaxPriceQuery = "DELETE FROM CARS WHERE price = (SELECT MAX(price) from CARS)";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteCarWithMaxPriceQuery);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public void addNewCarIfItsPriceHighest(Car car) {
        Float carPrice = car.getPrice();
        List<Car> listOfCarInTableWithHigherPrice = findAll().stream()
                .filter(x -> x.getPrice() > carPrice)
                .limit(1L)
                .collect(Collectors.toList());
        if (listOfCarInTableWithHigherPrice.size() == 0) {
            addNewCar(car);
        }
    }

    private Car parseResultSet(ResultSet rs) {

        Car car;

        try {
            car = new Car();
            car.setId(rs.getLong(ID)); //1 or id
            car.setName(rs.getString(NAME));
            car.setBrand(rs.getString(BRAND));
            car.setPrice(rs.getFloat(PRICE));
            car.setUser_id(rs.getLong(USER_ID));
            car.setCreated(rs.getTimestamp(CREATED));
            car.setChanged(rs.getTimestamp(CHANGED));
            car.setIs_deleted(rs.getBoolean(IS_DELETED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(properties.getUrl(), properties.getPort(), properties.getName());
        try {
            return DriverManager.getConnection(jdbcURL, properties.getLogin(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car findOne(Long id) {
        final String findOneById = "select * from cars where id = " + id;
        Car car;
        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findOneById)
        ) {
            rs.next();
            car = parseResultSet(rs);
            return car;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
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

