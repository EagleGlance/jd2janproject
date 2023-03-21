package com.noirix.service;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAggServiceImpl implements UserAggregationService {

    private final UserRepository userRepository;

    @Override
    public Map<String, Object> getStats() {

        List<User> users = userRepository.findAll();
        User one = userRepository.findOne(2L);

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("allUsers", users);
        resultMap.put("oneUser", one);

        return resultMap;
    }
}
