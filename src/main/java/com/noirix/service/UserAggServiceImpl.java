package com.noirix.service;

import com.noirix.configuration.DatabaseProperties;
import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.repository.UserRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAggServiceImpl implements UserAggregationService {

    private final UserRepository userRepository = new UserRepositoryImpl(new DatabaseProperties());

    @Override
    public Map<String, Object> getStats() {

        List<User> users = userRepository.findAll();
        User one = userRepository.findOne(2L);
        userRepository.searchUser();

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("allUsers", users);
        resultMap.put("oneUser", one);

        return resultMap;
    }
}
