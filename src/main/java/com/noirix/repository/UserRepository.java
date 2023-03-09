package com.noirix.repository;

import com.noirix.domain.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<Long, User> {

    List<User> searchUser(String query, Double weight);
}
