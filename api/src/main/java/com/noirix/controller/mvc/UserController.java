package com.noirix.controller.mvc;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

//    GET + /users = findAllUsers
//    GET + /users/1 = findUserById
//    POST + /users = createUser

    private static final Logger log = Logger.getLogger(UserController.class);

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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

    //localhost:8080/users/search?query=some&weight=80
    @RequestMapping(value = "/search", method = RequestMethod.GET)
/*    public ModelAndView searchUserByParam(@RequestParam("query") String query,
                                          @RequestParam("weight") String weight) {*/

    public ModelAndView searchUserByParam(@Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        System.out.println(result);

        Double parsedWeight;

        try {
            parsedWeight = Double.parseDouble(criteria.getWeight());
        } catch (NumberFormatException e) {
            log.error("User param weight: " + criteria.getWeight() + " cannot be parsed to Double", e);
            parsedWeight = 50D;
        }

        List<User> searchList = userService.search(criteria.getQuery(), parsedWeight);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", "Search Result");
        modelAndView.addObject("users", searchList);

        modelAndView.setViewName("hello");

        return modelAndView;
    }


}
