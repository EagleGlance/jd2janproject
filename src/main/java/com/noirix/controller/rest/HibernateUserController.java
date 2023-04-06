package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.service.HibernateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/users")
@RequiredArgsConstructor
public class HibernateUserController {

    private final HibernateUserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
