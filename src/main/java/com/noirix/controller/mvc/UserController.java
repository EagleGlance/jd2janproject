package com.noirix.controller.mvc;

import com.noirix.controller.requests.SearchCriteria;
import com.noirix.domain.User;
import com.noirix.service.UserAggregationService;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAllUsers() {

        List<User> users = userService.findAll();

        String collect = users.stream().map(User::getLogin).collect
                (Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", collect);
        modelAndView.addObject("users", users);

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView findUsersById(@PathVariable String id) {

        Long parsedUserId;

        try {
            parsedUserId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("User id: " + id + " cannot be parsed Long", e);
            parsedUserId = 1L;
        }

        User user = userService.findById(parsedUserId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", user.getLogin());
        modelAndView.addObject("users", Collections.singletonList(user));

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)

//      public ModelAndView SearchUserByParam(@RequestParam String day)
        public ModelAndView SearchUserByParam(@Valid @ModelAttribute SearchCriteria criteria, BindingResult result) {

        int parseNumberOfDay;

        try {
            parseNumberOfDay = Integer.parseInt(criteria.getDay());
        } catch (NumberFormatException e) {
            log.error("Number of day: " + criteria.getDay() + " cannot be parsed to int ", e);
            parseNumberOfDay = 1;
        }

        List<User> searchList = userService.changedOverTime(parseNumberOfDay);

        String collect = searchList.stream().map(User::getLogin).collect
                (Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", collect);
        modelAndView.addObject("users", searchList);

        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
