package com.noirix.service.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        /*Validation layer*/
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        /*Validation layer*/
        return userRepository.findAll();
    }
    @Override
    public User create(User object) {
        /*Validation layer*/
        if (object.getPassword().length() < 8) {
            throw new RuntimeException("The password is too short!");
        }
        return userRepository.create(object);
    }

    @Override
    public User update(Long id, User object) {
        /*Validation layer*/
        return userRepository.update(id, object);
    }

    @Override
    public void delete(Long id) {
        /*Validation layer*/
        userRepository.delete(id);
    }

    @Override
    public List<User> changedOverTime(int number_of_days) {
        return userRepository.changedOverTime(number_of_days);
    }

    @Override
    public Map<String, String> emailAndPhoneNumber() {
        return userRepository.emailAndPhoneNumber();
    }
}