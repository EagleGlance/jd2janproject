package com.noirix.controller.mvc;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.noirix.repository.columns.UserColumns.EMAIL;
import static com.noirix.repository.columns.UserColumns.LOGIN;
import static com.noirix.repository.columns.UserColumns.PASSPORT_SERIES_AND_NUMBER;
import static com.noirix.repository.columns.UserColumns.PASSWORD;
import static com.noirix.repository.columns.UserColumns.PHONE_NUMBER;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {

        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User build = User.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .phone_number(request.getPhone_number())
                .email(request.getEmail())
                .passport_series_and_number(request.getPassport_series_and_number())
                .build();

        User createdUser = userService.create(build);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
}
