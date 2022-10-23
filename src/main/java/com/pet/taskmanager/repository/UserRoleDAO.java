package com.pet.taskmanager.repository;

import com.pet.taskmanager.entity.Role;

public interface UserRoleDAO {
    void add(String sub, Integer role_id);
}
