package com.noirix.controller.converter;

import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.springdata.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, HibernateUser> {

    private final UserDataRepository repository;

    @Override
    public HibernateUser convert(UserUpdateRequest source) {

        Optional<HibernateUser> user = repository.findById(source.getId());
        return doConvert(user.orElseThrow(EntityNotFoundException::new), source);
    }
}
