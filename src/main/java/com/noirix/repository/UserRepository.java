package com.noirix.repository;

import com.noirix.domain.User;

public interface UserRepository extends CRUDRepository<Long, User> {

    void searchUser();
}
