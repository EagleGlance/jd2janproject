package com.noirix.repository.impl;

import com.noirix.constans.Milliseconds;
import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Primary
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users order by id desc ", userRowMapper);
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(Long id, User object) {
        return null;
    }

    @Override
    public void delete(Long id) {

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
        return null;
    }
}