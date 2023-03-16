package com.noirix.repository;

import com.noirix.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserSecondRepositoryImpl implements UserRepository {
    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(Long id, User object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> changedOverTime(int number_of_days) {
        return null;
    }

    @Override
    public Map<String, String> emailAndPhoneNumber() {
        return null;
    }
}