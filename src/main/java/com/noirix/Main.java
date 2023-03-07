package com.noirix;

import com.noirix.configuration.DatabaseProperties;
import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.UserRepositoryImpl;

import java.util.List;

public class Main {

    //Ctrl+Alt+O - import optimizing
    //Ctrl+Alt+L - formatting

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl(new DatabaseProperties());

        List<User> all = userRepository.findAll();

        for (User user : all) {
            System.out.println(user);
        }

    }
}