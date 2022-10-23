package com.pet.taskmanager.service;

import com.pet.taskmanager.entity.Role;

import java.util.Set;

public interface UserRoleService {
    void add(String sub, Integer role_id);
    Set<Role> getRolesByUsername(String username);
    Set<Role> getRolesBySub(String sub);
}
