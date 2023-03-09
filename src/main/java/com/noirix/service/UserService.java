package com.noirix.service;

import com.noirix.domain.User;

import java.util.List;

public interface UserService {
    User findOne(Long id);

    List<User> findAll();

    User create(User object);

    User update(User object);

    void delete(Long id);

    List<User> search(String query, Double weight);
}
