package com.pet.taskmanager.repository.impl;

import com.pet.taskmanager.entity.Project;
import com.pet.taskmanager.repository.ProjectDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private final JdbcTemplate jdbc;

    private final RowMapper<Project> mapper;

    public ProjectDAOImpl(DataSource ds, RowMapper<Project> mapper) {
        this.jdbc = new JdbcTemplate(ds);
        this.mapper = mapper;
    }

    @Override
    public Project create(Project project) {
        String projectId = UUID.randomUUID().toString();
        final String INSERT_PROJECT = "insert into project(project_id, name, key, lead, is_private, img) values(?,?,?,?,?,?) ";
        jdbc.update(
                INSERT_PROJECT,
                projectId,
                project.getName(),
                project.getKey(),
                project.getLead(),
                project.getIsPrivate(),
                project.getImg()
                    );
        return findOneById(projectId);

    }

    @Override
    public Project update(Project project) {
        final String UPDATE_PROJECT = "update project set name = ?, lead = ?, is_private = ?, img= ? where project_id = ? ";
        jdbc.update(
                UPDATE_PROJECT,
                project.getName(),
                project.getLead(),
                project.getIsPrivate(),
                project.getImg(),
                project.getProjectID()
        );
        return findOneById(project.getProjectID());

    }

    @Override
    public Project findOneById(String id) {
        final String SELECT_FROM_PROJECT = "select * from project where project_id = ?";
        return jdbc.queryForObject(SELECT_FROM_PROJECT, mapper, id);
    }

    @Override
    public void delete(String id) {
        final String DELETE_PROJECT = "DELETE FROM project WHERE project_id = ?";
        jdbc.update(DELETE_PROJECT, id);
    }

    @Override
    public List<Project> findAll() {
        final String SELECT_ALL_PROJECTS = "select * from project";
        return jdbc.query(SELECT_ALL_PROJECTS, mapper);
    }
}
