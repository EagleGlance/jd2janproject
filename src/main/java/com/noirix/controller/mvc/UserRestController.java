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
                .name(request.getName())
                .surname(request.getSurname())
                .fullName(request.getFullName())
                .birthDate(request.getBirthDate())
                .weight(request.getWeight())
                .build();

        User createdUser = userService.create(build);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    //Domain - Car
    //GET + /rest/cars - findAll
    //GET + /rest/cars/{id} - findOne
    //POST + /rest/cars - create object
    //PUT + /rest/cars - update object
    //DELETE + /rest/cars - update object

    //PATCH + /rest/cars  - partial update of object
    //GET + /rest/cars/search
    //GET + /rest/cars/search
    //PUT + /rest/cars/calculate
    //query - поисковой запрос
    //limit/offset = page = ограничение на число выводимых объектов

}
