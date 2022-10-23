package com.pet.taskmanager.service.impl;

import com.pet.taskmanager.entity.UserProfile;
import com.pet.taskmanager.repository.UserProfileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service("udm")
@RequiredArgsConstructor
public class UserDetailsManagerImpl implements UserDetailsManager {
    private final UserProfileDAO<UserProfile> dao;
    @Override
    public void createUser(UserDetails user) {
        dao.createUser(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserProfile loadUserByUsername(String username) throws UsernameNotFoundException {

        return dao.loadUserByUsername(username);
    }
}
