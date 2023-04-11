package com.noirix.util;

import com.noirix.domain.hibernate.HibernateUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserFieldsGenerator {

    public String generateEmail(HibernateUser user) {
        return user.getName() + "_" + user.getSurname() + "_" + user.getBirthDate().getTime() + "@gmail.com";
    }

    public String generatePassword() {
        return RandomStringUtils.random(20, true, true);
    }
}
