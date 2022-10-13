package com.pet.taskmanager.repository.impl;

import com.pet.taskmanager.entity.Project;
import com.pet.taskmanager.repository.ProjectDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private final JdbcTemplate jdbc;

    public ProjectDAOImpl(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
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
                project.getImg());
        return findOneById(projectId);

    }

    @Override
    public Project update(Project project) {
        return null;
    }

    @Override
    public Project findOneById(String id) {
        final String SELECT_FROM_PROJECT = "select * from project where project_id = ?";
        return jdbc.query(SELECT_FROM_PROJECT, rs -> {
            Project project = new Project();
            while (rs.next()) {
                project.setProjectID(rs.getString("PROJECT_ID"));
                project.setName(rs.getString("NAME"));
                project.setImg(rs.getString("IMG"));
                project.setLead(rs.getString("LEAD"));
                project.setIsPrivate(rs.getBoolean("IS_PRIVATE"));
                project.setKey(rs.getString("KEY"));
            }
            return project;
        }, id);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Project> findAll() {
        return null;
    }
}
