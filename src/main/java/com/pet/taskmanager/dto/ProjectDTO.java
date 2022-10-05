package com.pet.taskmanager.dto;

public record ProjectDTO(String projectID,
                         String name,
                         String key,
                         String lead,
                         boolean isPrivate,
                         String img) {

}
