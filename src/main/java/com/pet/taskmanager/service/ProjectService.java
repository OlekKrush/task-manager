package com.pet.taskmanager.service;

import com.pet.taskmanager.CRUD;
import com.pet.taskmanager.dto.ProjectDTO;

import java.util.List;

public interface ProjectService extends CRUD<ProjectDTO, String> {
    List<ProjectDTO> findByLead(String leadId);
}
