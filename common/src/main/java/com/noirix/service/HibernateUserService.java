package com.noirix.service;

import com.noirix.domain.Role;
import com.noirix.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserService {

    HibernateUser findOne(Long id);

    List<HibernateUser> findAll();

    HibernateUser create(HibernateUser object);

    HibernateUser update(HibernateUser object);

    void delete(Long id);

    List<HibernateUser> search(String query, Double weight);

    List<Role> getHibernateUserAuthorities(Long userId);

    Optional<HibernateUser> findByEmail(String email);
}
