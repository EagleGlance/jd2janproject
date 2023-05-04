package com.noirix.controller.rest;

import com.noirix.controller.exceptions.IllegalRequestException;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.AuthenticationInfo;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.service.HibernateUserService;
import com.noirix.util.UserFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
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
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        //Spring Converter: request -> entity
        //HibernateUser hibernateUser = converterService.convert(request, HibernateUser.class);

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateUser hibernateUser = HibernateUser.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .birthDate(request.getBirthDate().toLocalDateTime())
                .weight(request.getWeight())
                .gender(Gender.NOT_SELECTED)
                .fullName(request.getFullName())
                .build();

        String generateEmail = emailGenerator.generateEmail(hibernateUser);
        String generatePassword = emailGenerator.generatePassword();
        AuthenticationInfo info = new AuthenticationInfo(generateEmail, generatePassword);

        hibernateUser.setAuthenticationInfo(info);

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
        one.setBirthDate(request.getBirthDate().toLocalDateTime());
        one.setWeight(request.getWeight());
        one.setGender(request.getGender());
        one.setFullName(request.getFullName());

        one = userService.update(one);
        return new ResponseEntity<>(one, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUsersByFullName(@Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {
        System.out.println(result);

        Double parsedWeight;

        try {
            parsedWeight = Double.parseDouble(criteria.getWeight());
        } catch (NumberFormatException e) {
            parsedWeight = 50D;
        }

        List<HibernateUser> searchList = userService.search(criteria.getQuery(), parsedWeight);

        return new ResponseEntity<>(Collections.singletonMap("users", searchList), HttpStatus.OK);
    }

}
