package com.noirix.service;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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