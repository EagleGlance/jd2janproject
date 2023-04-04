package com.noirix.service;

import com.noirix.domain.Role;

import java.util.List;

public interface RoleService {
    Role findOne(Long id);

    List<Role> findAll();

    Role create(Role object);

    Role update(Role object);

    void delete(Long id);

    List<Role> getUserAuthorities(Long userId);
}
