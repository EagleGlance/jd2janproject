package com.noirix.repository.rowmapper;

import com.noirix.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.noirix.repository.columns.UserColumns.CHANGED;
import static com.noirix.repository.columns.UserColumns.CREATED;
import static com.noirix.repository.columns.UserColumns.EMAIL;
import static com.noirix.repository.columns.UserColumns.ID;
import static com.noirix.repository.columns.UserColumns.LOGIN;
import static com.noirix.repository.columns.UserColumns.PASSPORT_SERIES_AND_NUMBER;
import static com.noirix.repository.columns.UserColumns.PASSWORD;
import static com.noirix.repository.columns.UserColumns.PHONE_NUMBER;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;

        try {
            user = User.builder()
                    .id(rs.getLong(ID))
                    .login(rs.getString(LOGIN))
                    .password(rs.getString(PASSWORD))
                    .phone_number(rs.getString(PHONE_NUMBER))
                    .email(rs.getString(EMAIL))
                    .passport_series_and_number(rs.getString(PASSPORT_SERIES_AND_NUMBER))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
