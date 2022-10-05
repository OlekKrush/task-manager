package com.pet.taskmanager.controller.impl;

import com.pet.taskmanager.dto.ProjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectControllerImpl implements ProjectController {

    @Override
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDTO create(@RequestBody ProjectDTO projectDTO) {
        System.out.println(projectDTO);
        return new ProjectDTO("test-id","test-name", "test-key", "test-lead", true, "test-img"  );
    }

    @PostMapping(value = "/jopka")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDTO createJopka(@RequestBody ProjectDTO projectDTO) {
        System.out.println(projectDTO);
        return new ProjectDTO("test-id","test-name", "test-key", "test-lead", true, "test-img"  );
    }

    @Override
    @PutMapping(value = "/{id}")
    public ProjectDTO update(@RequestBody ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public ProjectDTO findOneById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProjectDTO> findAll() {
        return null;
    }
}
