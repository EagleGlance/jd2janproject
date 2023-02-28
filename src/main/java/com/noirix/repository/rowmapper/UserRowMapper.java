package com.noirix.repository.rowmapper;

import com.noirix.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.UserColumns.BIRTH_DATE;
import static com.noirix.repository.columns.UserColumns.FULL_NAME;
import static com.noirix.repository.columns.UserColumns.ID;
import static com.noirix.repository.columns.UserColumns.NAME;
import static com.noirix.repository.columns.UserColumns.SURNAME;
import static com.noirix.repository.columns.UserColumns.WEIGHT;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;

        try {
            user = new User();
            user.setId(rs.getLong(ID));
            user.setName(rs.getString(NAME));
            user.setSurname(rs.getString(SURNAME));
            user.setBirthDate(rs.getTimestamp(BIRTH_DATE));
            user.setFullName(rs.getString(FULL_NAME));
            user.setWeight(rs.getDouble(WEIGHT));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
