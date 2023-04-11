package com.noirix.controller.rest;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.service.HibernateUserService;
import com.noirix.util.UserFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/users")
@RequiredArgsConstructor
public class HibernateUserController {

    private final HibernateUserService userService;

    private final UserFieldsGenerator emailGenerator;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserCreateRequest request) {

        //Spring Converter: request -> entity
        //HibernateUser hibernateUser = converterService.convert(request, HibernateUser.class);

        HibernateUser hibernateUser = HibernateUser.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate())
                .weight(request.getWeight())
                .gender(Gender.NOT_SELECTED)
                .fullName(request.getFullName())
                .password(emailGenerator.generatePassword())
                .build();

        hibernateUser.setEmail(emailGenerator.generateEmail(hibernateUser));

        hibernateUser = userService.create(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(Principal principal, @RequestBody UserUpdateRequest request) {

        //Spring Converter: request -> entity
        //HibernateUser hibernateUser = converterService.convert(request, HibernateUser.class);

        HibernateUser one = userService.findOne(request.getId());

        one.setId(request.getId());
        one.setName(request.getName());
        one.setSurname(request.getSurname());
        one.setBirthDate(request.getBirthDate());
        one.setWeight(request.getWeight());
        one.setGender(Gender.valueOf(request.getGender()));
        one.setFullName(request.getFullName());

        one = userService.update(one);
        return new ResponseEntity<>(one, HttpStatus.CREATED);
    }

}
