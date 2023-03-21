package com.noirix.controller.rest;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spring")
@RequiredArgsConstructor
public class SpringInvestigationController {

    private final List<UserRepository> repositoryList;

    @GetMapping("/info")
    public void getSpringInfo() {

        for (UserRepository userRepository : repositoryList) {
            if (userRepository.support("jdbc")) {
                for (User user : userRepository.findAll()) {
                    System.out.println(user);
                }
            }
        }

    }
}
