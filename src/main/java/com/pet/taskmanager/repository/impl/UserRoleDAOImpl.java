package com.pet.taskmanager.repository.impl;

import com.pet.taskmanager.exception.CustomException;
import com.pet.taskmanager.repository.UserRoleDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Log4j2
public class UserRoleDAOImpl implements UserRoleDAO {
    private final JdbcTemplate jdbc;

    public UserRoleDAOImpl (DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }
    @Override
    public void add(String sub, Integer role_id) {
        final String SQL_ROLE = """
                insert into user_role (sub, role_id) values (?,?)
                """;
        try {
           jdbc.update(SQL_ROLE, sub, role_id);
        } catch (DataAccessException e) {
            //TODO
            throw new CustomException("TODO");
        }
    }
}
