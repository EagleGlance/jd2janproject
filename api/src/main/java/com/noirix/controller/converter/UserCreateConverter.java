package com.noirix.controller.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.AuthenticationInfo;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.security.config.JWTConfiguration;
import com.noirix.util.UserFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    private final UserFieldsGenerator fieldsGenerator;

    private final JWTConfiguration configuration;

    private final PasswordEncoder encoder;

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser hibernateUser = new HibernateUser();

        String generateEmail = fieldsGenerator.generateEmail(hibernateUser);
        String generatePassword = fieldsGenerator.generatePassword();

        String resultPassword = generatePassword + configuration.getServerPasswordSalt();
        String encode = encoder.encode(resultPassword);

        AuthenticationInfo info = new AuthenticationInfo(generateEmail, encode);

        hibernateUser.setAuthenticationInfo(info);

        return doConvert(hibernateUser, request);
    }
}
