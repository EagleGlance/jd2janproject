package com.noirix.service;

import com.noirix.domain.Role;
import com.noirix.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findOne(Long id);

    List<User> findAll();

    User create(User object);

    User update(User object);

    void delete(Long id);

    List<User> search(String query, Double weight);

    List<Role> getUserAuthorities(Long userId);

    Optional<User> findByEmail(String email);
}
