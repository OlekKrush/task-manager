package com.pet.taskmanager.controller.impl;

import com.pet.taskmanager.component.AuthManager;
import com.pet.taskmanager.dto.AuthUserDetails;
import com.pet.taskmanager.dto.JWT;
import com.pet.taskmanager.entity.UserProfile;
import com.pet.taskmanager.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthManager auth;
    private final JwtService  jwt;


    @PostMapping("auth/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public JWT registration(@RequestBody AuthUserDetails authRequest) {
        UserProfile authenticate = auth.registration(authRequest.username(), authRequest.password());
        String      token        = jwt.generateToken(authenticate, new HashMap<>());
        return new JWT(token);
    }
    /*@PostMapping("auth/default")
    @ResponseStatus(HttpStatus.OK)
    public JWT authentication(@RequestBody AuthUserDetails authRequest) {

        UserDetails authenticate = auth.authenticate(authRequest.getUsername(), authRequest.getPassword());
        String      token        = jwtService.generateToken(authenticate, new HashMap<>());

        return new JWT(token);
    }

    @PostMapping("auth/check")
    @ResponseStatus(HttpStatus.OK)
    public Boolean tokenValidation(@RequestBody JWT token) {
        return jwtService.validateToken(token.getToken());
    }
*/

}
