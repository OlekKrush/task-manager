package com.pet.taskmanager.controller;

import com.pet.taskmanager.CRUD;
import com.pet.taskmanager.dto.ProjectDTO;
import org.springframework.http.RequestEntity;

public interface ProjectController extends CRUD<ProjectDTO, String> {
    ProjectDTO update(RequestEntity<ProjectDTO> entity);
}
