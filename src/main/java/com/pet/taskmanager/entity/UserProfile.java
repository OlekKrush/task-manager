package com.pet.taskmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class UserProfile extends User {

    private String sub;
    private String familyName;
    private String givenName;
    private String picture;


    public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       String sub, String familyName,
                       String givenName, String picture) {
        super(username, password, authorities);
        this.sub = sub;
        this.familyName = familyName;
        this.givenName = givenName;
        this.picture = picture;
    }
}
