package com.pet.taskmanager.controller.impl;

import com.pet.taskmanager.controller.ProjectController;
import com.pet.taskmanager.dto.ProjectDTO;
import com.pet.taskmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        return null;
    }


//    @PostMapping(value = "/jopka")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public ProjectDTO createJopka(@RequestBody ProjectDTO projectDTO) {
//        System.out.println(projectDTO);
//        return new ProjectDTO("test-id", "test-name", "test-key", "test-lead", true, "test-img");
//    }

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

}
