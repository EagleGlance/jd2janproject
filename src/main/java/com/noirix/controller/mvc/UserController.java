package com.noirix.controller.mvc;

import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

//    GET + /users = findAllUsers
//    GET + /users/1 = findUserById
//    POST + /users = createUser

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

}
