package com.noirix.repository.impl.user;

import com.noirix.constans.Milliseconds;
import com.noirix.domain.User;
import com.noirix.configuration.DatabaseProperties;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

import static com.noirix.repository.columns.UserColumns.CHANGED;
import static com.noirix.repository.columns.UserColumns.CREATED;
import static com.noirix.repository.columns.UserColumns.EMAIL;
import static com.noirix.repository.columns.UserColumns.ID;
import static com.noirix.repository.columns.UserColumns.LOGIN;
import static com.noirix.repository.columns.UserColumns.PASSPORT_SERIES_AND_NUMBER;
import static com.noirix.repository.columns.UserColumns.PASSWORD;
import static com.noirix.repository.columns.UserColumns.PHONE_NUMBER;

@Repository
//@Primary
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DatabaseProperties properties;
    private final Logger logger = Logger.getLogger(UserRepositoryImpl.class);


    private User parseResultSet(ResultSet rs) {

        User user;

        try {
            user = User.builder()
                    .id(rs.getLong(ID))
                    .login(rs.getString(LOGIN))
                    .password(rs.getString(PASSWORD))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .email(rs.getString(EMAIL))
                    .passportSeriesAndNumber(rs.getString(PASSPORT_SERIES_AND_NUMBER))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void registerDriver() {
        try {
            Class.forName(properties.getDriverNAME());
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
    public List<User> findAll() {

        logger.info("Start of findAll method");

        final String findAllQuery = "select * from users order by id desc";

        List<User> result = new ArrayList<>();

        registerDriver();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(findAllQuery)){

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }

            logger.info("End of findAll method");

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
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
        final String createQuery = "insert into users (id, login, password, phone_number, email, passport_series_and_number,created, changed) values(?, ?, ?, ?, ?, ?, ?, ?)";
        registerDriver();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassportSeriesAndNumber());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
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
            preparedStatement.setString(3, user.getPhoneNumber());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassportSeriesAndNumber());
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
            String phone = user.getPhoneNumber();
            emailAndPhone.put(email, phone);
        }
        return emailAndPhone;
    }
}