package com.pet.taskmanager.component;

import com.pet.taskmanager.entity.UserProfile;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Log4j2
public class AuthManager {

    private final UserDetailsManager userDetailsManager;

    public AuthManager(@Qualifier("udm") UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public UserProfile registration(String username, String password) {
        User user = new User(
                username,
                password,
                new ArrayList<>()
        );
        userDetailsManager.createUser(user);

        return (UserProfile) userDetailsManager.loadUserByUsername(user.getUsername());
    }
}
