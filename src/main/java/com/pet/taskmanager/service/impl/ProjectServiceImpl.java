package com.pet.taskmanager.service.impl;

import com.pet.taskmanager.dto.ProjectDTO;
import com.pet.taskmanager.entity.Project;
import com.pet.taskmanager.mapper.mapstuct.ProjectMapper;
import com.pet.taskmanager.repository.ProjectDAO;
import com.pet.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDAO    dao;
    private final ProjectMapper mapper;

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Project project = mapper.toEntity(projectDTO);
        Project result  = dao.create(project);
        return mapper.toDTO(result);
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        Project project = mapper.toEntity(projectDTO);
        Project result  = dao.update(project);
        return mapper.toDTO(result);
    }

    @Override
    public ProjectDTO findOneById(String id) {
        Project result = dao.findOneById(id);
        return mapper.toDTO(result);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return dao
                .findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<ProjectDTO> findByLead(String leadId) {
        return null;
    }
}
