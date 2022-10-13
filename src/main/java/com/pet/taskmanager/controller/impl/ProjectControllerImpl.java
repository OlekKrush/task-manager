package com.pet.taskmanager.controller.impl;

import com.pet.taskmanager.controller.ProjectController;
import com.pet.taskmanager.dto.ProjectDTO;
import com.pet.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService service;

    @Override
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDTO create(@RequestBody ProjectDTO projectDTO) {
//        System.out.println(projectDTO);
        return service.create(projectDTO);
//        return new ProjectDTO("test-id","test-name", "test-key", "test-lead", true, "test-img"  );
    }

//    @PostMapping(value = "/jopka")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public ProjectDTO createJopka(@RequestBody ProjectDTO projectDTO) {
//        System.out.println(projectDTO);
//        return new ProjectDTO("test-id", "test-name", "test-key", "test-lead", true, "test-img");
//    }

    @Override
    @PutMapping(value = "/{id}")
    public ProjectDTO update(@RequestBody ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public ProjectDTO findOneById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<ProjectDTO> findAll() {
        return null;
    }
}
