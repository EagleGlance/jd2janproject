package com.noirix.controller.converter;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public HibernateUser doConvert(HibernateUser userForUpdate, UserCreateRequest request) {

        userForUpdate.setName(request.getName());
        userForUpdate.setSurname(request.getSurname());
        userForUpdate.setBirthDate(request.getBirthDate());
        userForUpdate.setWeight(request.getWeight());
        userForUpdate.setGender(Gender.valueOf(request.getGender()));
        userForUpdate.setFullName(request.getFullName());

        return userForUpdate;
    }

}
