package com.pet.taskmanager.controller.impl;

import com.pet.taskmanager.controller.ProjectController;
import com.pet.taskmanager.dto.ProjectDTO;
import com.pet.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Log4j2
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService service;

    @Override
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDTO create(@RequestBody ProjectDTO projectDTO) {

        return service.create(projectDTO);
    }



    @Override
    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProjectDTO update(RequestEntity<ProjectDTO> entity) {
        System.out.println(entity);
        return service.update(entity.getBody());
    }

    @Override
    @GetMapping(value = "/{id}")
    public ProjectDTO findOneById(@PathVariable String id) {
        return service.findOneById(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<ProjectDTO> findAll() {
        return service.findAll();
    }
    @Override
    @Deprecated
    public ProjectDTO update(ProjectDTO projectDTO) {
        return null;
    }
}
