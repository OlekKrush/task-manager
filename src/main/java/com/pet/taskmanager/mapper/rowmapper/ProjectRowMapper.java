package com.pet.taskmanager.mapper.rowmapper;


import com.pet.taskmanager.entity.Project;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProjectID(rs.getString("PROJECT_ID"));
        project.setName(rs.getString("NAME"));
        project.setImg(rs.getString("IMG"));
        project.setLead(rs.getString("LEAD"));
        project.setIsPrivate(rs.getBoolean("IS_PRIVATE"));
        project.setKey(rs.getString("KEY"));
        return project;
    }
}
