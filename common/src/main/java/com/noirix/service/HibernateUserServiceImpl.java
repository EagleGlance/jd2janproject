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
        return userRepository.findOne(id);
    }

    @Override
    public List<HibernateUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return userRepository.create(object);
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return userRepository.update(object);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HibernateUser> search(String query, Double weight) {
        return userRepository.searchUser(query, weight);
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
