package com.noirix.service;

import com.noirix.domain.Role;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.hibernate.HibernateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HibernateUserServiceImpl implements HibernateUserService {

    private final HibernateUserRepository userRepository;

    @Override
    public HibernateUser findOne(Long id) {
        return null;
    }

    @Override
    public List<HibernateUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return null;
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HibernateUser> search(String query, Double weight) {
        return null;
    }

    @Override
    public List<Role> getHibernateUserAuthorities(Long userId) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findByEmail(String email) {
        return Optional.empty();
    }
}
