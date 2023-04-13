package com.noirix.service;
import com.noirix.domain.User;
import java.util.List;
import java.util.Map;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    User create(User object);

    User update(Long id, User object);

    void delete(Long id);

    List<User> changedOverTime(int number_of_days);
    Map<String, String> emailAndPhoneNumber();
}