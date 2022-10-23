package com.pet.taskmanager.service;


import com.pet.taskmanager.entity.UserProfile;

import java.util.Map;

public interface JwtService {

    String generateToken(UserProfile userDetails, Map<String, Object> claims);
    Boolean validateToken(String token);
    UserProfile getUserDetails(String token);
}
