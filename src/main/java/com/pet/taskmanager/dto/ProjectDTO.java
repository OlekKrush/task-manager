package com.pet.taskmanager.dto;

public record ProjectDTO(
    String projectId,
    String name,
    String key,
    String lead,
    Boolean isPrivate,
    String img
  ){}
