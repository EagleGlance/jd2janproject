package com.noirix.repository;

import com.noirix.domain.User;
import java.util.List;
import java.util.Map;

public interface UserRepository extends CRUDRepository <Long, User> {

    List<User> changedOverTime(int numberOfDays);
    Map<String, String> emailAndPhoneNumber();
}