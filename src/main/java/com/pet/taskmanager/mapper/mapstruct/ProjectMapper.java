package com.pet.taskmanager.mapper.mapstruct;

import com.pet.taskmanager.dto.ProjectDTO;
import com.pet.taskmanager.entity.Project;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {

    ProjectDTO toDTO(Project entity);

    Project toEntity(ProjectDTO dto);
}
