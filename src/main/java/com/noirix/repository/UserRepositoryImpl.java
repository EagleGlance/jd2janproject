package com.noirix.repository;

import com.noirix.constans.Milliseconds;
import com.noirix.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/db1";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "postgres";

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String PASSPORT_SERIES_AND_NUMBER = "passport_series_and_number";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";


    private User parseResultSet(ResultSet rs) {

        User user;

        try {
            user = new User();
            user.setId(rs.getLong(ID)); //1 or id
            user.setLogin(rs.getString(LOGIN));
            user.setPassword(rs.getString(PASSWORD));
            user.setPhone_number(rs.getString(PHONE_NUMBER));
            user.setEmail(rs.getString(EMAIL));
            user.setPassport_series_and_number(rs.getString(PASSPORT_SERIES_AND_NUMBER));
            user.setCreated(rs.getTimestamp(CREATED));
            user.setChanged(rs.getTimestamp(CHANGED));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void registerDriver() {
        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
    }

    private Connection getConnection() {
        String jdbcURL = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);
        try {
            return DriverManager.getConnection(jdbcURL, DATABASE_LOGIN, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {

        final String findAllQuery = "select * from users order by id desc";

        List<User> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)){

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
        return result;
    }

    @Override
    public User findById(Long id) {
        final String findOneQuery = "select * from users where id = ?";
        registerDriver();
        List <User> user = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findOneQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                user.add(parseResultSet(rs));
            }
        }
        catch (SQLException exp)
        {
            throw new RuntimeException();
        }
        return user.size() != 0 ? user.get(0) : null;
    }

    @Override
    public User create(User user) {
        final String createQuery = "insert into users (id, login, password, phone_number, email, passport_series_and_number, created, changed)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone_number());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassport_series_and_number());
            preparedStatement.setTimestamp(7, user.getCreated());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException exp)
        {
            throw new RuntimeException();
        }
        return findById(user.getId());
    }

    @Override
    public User update(Long id, User user)
    {
        final String updateQuery = "update users set login = ?, password = ?, phone_number = ?, email = ?, " +
                "passport_series_and_number = ?, changed = ? where id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone_number());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassport_series_and_number());
            preparedStatement.setTimestamp(6, user.getChanged());
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exp)
        {
            throw new RuntimeException();
        }
        return this.findById(id);
    }

    @Override
    public void delete(Long id)
    {
        User deleteUser = findById(id);
        final String deleteQuery = "update users set changed = ?, is_deleted = true where id = ?";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exp)
        {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> changedOverTime(int numberOfDays) {
        List<User> result = new ArrayList<>();
        List <User> users = findAll();
        for (User user: users) {
            long time = Timestamp.valueOf(LocalDateTime.now()).getTime() - user.getChanged().getTime();
            int days_between = (int)(time / Milliseconds.DAY);
            if (days_between <= numberOfDays)
            {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public Map<String, String> emailAndPhoneNumber() {
        List<User> users = findAll();
        Map<String, String> emailAndPhone = new HashMap<>();
        for (User user:users)
        {
            String email = user.getEmail();
            String phone = user.getPhone_number();
            emailAndPhone.put(email, phone);
        }
        return emailAndPhone;
    }
}