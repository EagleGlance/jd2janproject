package com.noirix.controller.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.AuthenticationInfo;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.util.UserFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    private final UserFieldsGenerator fieldsGenerator;

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser hibernateUser = new HibernateUser();

        String generateEmail = fieldsGenerator.generateEmail(hibernateUser);
        String generatePassword = fieldsGenerator.generatePassword();
        AuthenticationInfo info = new AuthenticationInfo(generateEmail, generatePassword);

        hibernateUser.setAuthenticationInfo(info);

        return doConvert(hibernateUser, request);
    }
}
