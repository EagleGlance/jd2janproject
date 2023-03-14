package com.noirix.repository.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Order(1)
@RequiredArgsConstructor
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;

    @Override
    public User findOne(Long id) {
        return jdbcTemplate.queryForObject("select * from users where id = " + id, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users order by id desc", userRowMapper);
    }

    @Override
    public User create(User object) {
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
    public List<User> searchUser(String query, Double weight) {

        final String sqlQuery =
                "select * " +
                        " from users " +
                        " where lower(name) like '%" + query + "%' and " +
                        " weight > " + weight +
                        " order by id desc";

        return jdbcTemplate.query(sqlQuery, userRowMapper);
    }

    @Override
    public boolean support(String param) {
        return param.equalsIgnoreCase("jdbctemplate");
    }
}
