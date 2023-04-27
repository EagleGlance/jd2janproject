package com.noirix.controller.rest;

import com.noirix.domain.Role;
import com.noirix.domain.User;
import com.noirix.service.RoleService;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRoles() {

        List<Role> roles = roleService.findAll();

        return new ResponseEntity<>(Collections.singletonMap("roles", roles), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUsersAuthorities(@PathVariable Long userId) {

        User user = userService.findOne(userId);
        List<Role> roles = roleService.getUserAuthorities(userId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("user", user);
        result.put("roles", roles);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
