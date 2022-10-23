package com.pet.taskmanager.dto;


import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class JWT {

    @NonNull
    private String token;

    public JWT() {}

    public JWT(String token) {
        this.token = token;
    }

}
