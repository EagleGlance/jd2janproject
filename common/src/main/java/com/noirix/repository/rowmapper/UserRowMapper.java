package com.noirix.repository.rowmapper;

import com.noirix.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.UserColumns.BIRTH_DATE;
import static com.noirix.repository.columns.UserColumns.EMAIL;
import static com.noirix.repository.columns.UserColumns.FULL_NAME;
import static com.noirix.repository.columns.UserColumns.ID;
import static com.noirix.repository.columns.UserColumns.NAME;
import static com.noirix.repository.columns.UserColumns.PASSWORD;
import static com.noirix.repository.columns.UserColumns.SURNAME;
import static com.noirix.repository.columns.UserColumns.WEIGHT;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;

        try {

            user = User.builder()
                    .id(rs.getLong(ID))
                    .name(rs.getString(NAME))
                    .surname(rs.getString(SURNAME))
                    .birthDate(rs.getTimestamp(BIRTH_DATE))
                    .fullName(rs.getString(FULL_NAME))
                    .weight(rs.getDouble(WEIGHT))
                    .email(rs.getString(EMAIL))
                    .password(rs.getString(PASSWORD))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
