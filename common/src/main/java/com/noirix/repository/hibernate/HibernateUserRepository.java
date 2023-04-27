package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateUserRepository extends CRUDRepository<Long, HibernateUser> {

    List<HibernateUser> searchUser(String query, Double weight);

    Optional<HibernateUser> findByEmail(String email);
}
