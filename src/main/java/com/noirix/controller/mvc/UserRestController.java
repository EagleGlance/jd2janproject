package com.noirix.controller.mvc;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private static final Logger log = Logger.getLogger(UserRestController.class);

    private Long parsedUserId(String id) {

        long parsedUserId;

        try {
            parsedUserId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("User id: " + id + " cannot be parsed Long", e);
            parsedUserId = 1L;
        }

        return parsedUserId;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {

        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findUsersById(@PathVariable String id) {

        User user = userService.findById(parsedUserId(id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User build = User.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .passportSeriesAndNumber(request.getPassportSeriesAndNumber())
                .build();

        User createdUser = userService.create(build);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserCreateRequest request) {

        User build = User.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .passportSeriesAndNumber(request.getPassportSeriesAndNumber())
                .build();

        User createdUser = userService.update(parsedUserId(id), build);

        return new ResponseEntity<>(createdUser, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable String id) {

        userService.delete(parsedUserId(id));
    }
}
