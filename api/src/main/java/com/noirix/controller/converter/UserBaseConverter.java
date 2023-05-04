package com.noirix.controller.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public HibernateUser doConvert(HibernateUser userForUpdate, UserCreateRequest request) {

        userForUpdate.setName(request.getName());
        userForUpdate.setSurname(request.getSurname());

        Timestamp birthDate = request.getBirthDate();
        userForUpdate.setBirthDate(birthDate.toLocalDateTime());
        userForUpdate.setWeight(request.getWeight());
        userForUpdate.setGender(request.getGender());
        userForUpdate.setFullName(request.getFullName());

        return userForUpdate;
    }

}
