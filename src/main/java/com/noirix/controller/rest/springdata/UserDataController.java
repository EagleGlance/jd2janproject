package com.noirix.controller.rest.springdata;

import com.noirix.controller.exceptions.IllegalRequestException;
import com.noirix.controller.requests.SearchCriteria;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.AuthenticationInfo;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.springdata.UserDataRepository;
import com.noirix.util.UserFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/rest/springdata/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataRepository repository;

    private final UserFieldsGenerator fieldsGenerator;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/test")
    public ResponseEntity<Object> testSpringDataMethods() {

        List<HibernateUser> result =
                repository.findByHQLQuery("slava.kalevich.27@noirix.com", "Kalevich");
        //repository.findUsers();
        //List<Object[]> result = repository.findColumnsFromDifferentTables();

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Object> testEndpoint(@PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(page, 4))), HttpStatus.OK);
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
                .birthDate(request.getBirthDate())
                .weight(request.getWeight())
                .gender(Gender.NOT_SELECTED)
                .fullName(request.getFullName())
                .build();

        String generateEmail = fieldsGenerator.generateEmail(hibernateUser);
        String generatePassword = fieldsGenerator.generatePassword();
        AuthenticationInfo info = new AuthenticationInfo(generateEmail, generatePassword);

        hibernateUser.setAuthenticationInfo(info);

        hibernateUser = repository.save(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(Principal principal, @RequestBody UserUpdateRequest request) {

        //Spring Converter: request -> entity
        //HibernateUser hibernateUser = converterService.convert(request, HibernateUser.class);

        HibernateUser one = repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

        one.setId(request.getId());
        one.setName(request.getName());
        one.setSurname(request.getSurname());
        one.setBirthDate(request.getBirthDate());
        one.setWeight(request.getWeight());
        one.setGender(Gender.valueOf(request.getGender()));
        one.setFullName(request.getFullName());

        one = repository.save(one);
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

        List<HibernateUser> searchList = Collections.emptyList();//userService.search(criteria.getQuery(), parsedWeight);

        return new ResponseEntity<>(Collections.singletonMap("users", searchList), HttpStatus.OK);
    }
}
