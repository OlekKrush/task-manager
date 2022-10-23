package com.pet.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String                          sub;
    private String                          email;
    private boolean                         email_verified;
    private String                          family_name;
    private String                          given_name;
    private String                          name;
    private String                          local;
    private String                          picture;
    private Set<? extends GrantedAuthority> authorities;
}
