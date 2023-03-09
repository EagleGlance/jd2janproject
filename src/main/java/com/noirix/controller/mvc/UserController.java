package com.noirix.controller.mvc;

import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

//    GET + /users = findAllUsers
//    GET + /users/1 = findUserById
//    POST + /users = createUser

    private static final Logger log = Logger.getLogger(UserController.class);

    private final UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView findAllUsers() {

        List<User> users = userService.findAll();

        String collect = users.stream().map(User::getName).collect(Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", collect);
        modelAndView.addObject("users", users);

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    //localhost:8080/users/1
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView findUserById(@PathVariable String id) {

        Long parsedUserId;

        try {
            parsedUserId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("User id: " + id + " cannot be parsed to Long", e);
            parsedUserId = 1L;
        }

        User user = userService.findOne(parsedUserId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", user.getFullName());
        modelAndView.addObject("users", Collections.singletonList(user));

        modelAndView.setViewName("hello");

        return modelAndView;
    }


}
