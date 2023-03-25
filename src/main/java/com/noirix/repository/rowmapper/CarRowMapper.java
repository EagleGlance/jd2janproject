package com.noirix.repository.rowmapper;

import com.noirix.domain.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.CarColumns.ID;
import static com.noirix.repository.columns.CarColumns.NAME;
import static com.noirix.repository.columns.CarColumns.BRAND;
import static com.noirix.repository.columns.CarColumns.PRICE;
import static com.noirix.repository.columns.CarColumns.USER_ID;
import static com.noirix.repository.columns.CarColumns.CREATED;
import static com.noirix.repository.columns.CarColumns.CHANGED;
import static com.noirix.repository.columns.CarColumns.IS_DELETED;

@Component
public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
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
}

