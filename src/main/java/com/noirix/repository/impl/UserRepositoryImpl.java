package com.noirix.repository.impl;

import com.noirix.configuration.DatabaseProperties;
import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.noirix.repository.columns.UserColumns.BIRTH_DATE;
import static com.noirix.repository.columns.UserColumns.FULL_NAME;
import static com.noirix.repository.columns.UserColumns.ID;
import static com.noirix.repository.columns.UserColumns.NAME;
import static com.noirix.repository.columns.UserColumns.SURNAME;
import static com.noirix.repository.columns.UserColumns.WEIGHT;

@Repository
@RequiredArgsConstructor
@Primary
//bean id=userRepositoryImpl   class=UserRepositoryImpl
//@Component
public class UserRepositoryImpl implements UserRepository {

    private final DatabaseProperties properties;

    private final Logger logger = Logger.getLogger(UserRepositoryImpl.class);

    @Override
    public List<User> findAll() {

        /*
         * 1) Driver Manager - getting connection from DB
         * */

        logger.info("Start of findAll method");

        final String findAllQuery = "select * from users order by id desc";

        List<User> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)
        ) {

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }

            logger.info("End of findAll method");

            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("SQL Issues!");
        }
    }

    private User parseResultSet(ResultSet rs) {

        User user;

        try {
            user = new User();
            user.setId(rs.getLong(ID)); //1 or id
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
    public User findOne(Long id) {
        return findAll().stream().findFirst().get();
    }

    @Override
    public User create(User object) {


        //get last id from sequence
        //get object by id from table
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void searchUser() {

    }
}
