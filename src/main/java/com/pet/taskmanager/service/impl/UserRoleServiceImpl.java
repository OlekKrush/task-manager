package com.pet.taskmanager.service.impl;

import com.pet.taskmanager.entity.Role;
import com.pet.taskmanager.repository.UserRoleDAO;
import com.pet.taskmanager.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDAO dao;

    @Override
    public void add(String sub, Integer role_id) {
        dao.add(sub, role_id);
    }

    @Override
    public Set<Role> getRolesByUsername(String username) {
        return null;
    }

    @Override
    public Set<Role> getRolesBySub(String sub) {
        return null;
    }
}
