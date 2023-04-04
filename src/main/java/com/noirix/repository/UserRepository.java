package com.noirix.repository;

import com.noirix.domain.Role;
import com.noirix.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<Long, User> {

    List<User> searchUser(String query, Double weight);

    boolean support(String param);

    List<Role> getUserAuthorities(Long userId);

    Optional<User> findByEmail(String email);
}
