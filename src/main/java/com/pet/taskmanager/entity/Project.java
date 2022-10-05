package com.pet.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String projectID;
    private String name;
    private String key;
    private String lead;
    private Boolean isPrivate;
    private String img;


    public Project(String name, String key, String lead, Boolean isPrivate, String img) {
        this.name = name;
        this.key = key;
        this.lead = lead;
        this.isPrivate = isPrivate;
        this.img = img;
    }
}
