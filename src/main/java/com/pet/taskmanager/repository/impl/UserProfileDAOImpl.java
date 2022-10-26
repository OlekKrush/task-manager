package com.pet.taskmanager.repository.impl;

import com.pet.taskmanager.entity.Role;
import com.pet.taskmanager.entity.UserProfile;
import com.pet.taskmanager.exception.CustomException;
import com.pet.taskmanager.repository.UserProfileDAO;
import com.pet.taskmanager.service.UserRoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
@Log4j2
public class UserProfileDAOImpl implements UserProfileDAO<UserProfile> {
    private final JdbcTemplate    jdbc;
    private final UserRoleService urService;

    public UserProfileDAOImpl(DataSource ds, UserRoleService urService) {
        this.jdbc = new JdbcTemplate(ds);
        this.urService = urService;
    }

    @Override
    @Transactional
    public void createUser(UserDetails user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final String SQL = """
                insert into user_details (sub, email, password) values (?,?,?)
                """;

        String sub = UUID.randomUUID().toString();
        try {
            jdbc.update(SQL,
                    sub,
                    user.getUsername(),
                    encoder.encode(user.getPassword()));
            urService.add(sub, 1);
            urService.add(sub, 3);
        } catch (DataAccessException e) {
            log.error(String.format(
                    """
                            @method [void createUser(UserDetails user)] ->
                            @data [user = %1$s ] ->
                            @error: %2$s
                            """, user, e
            ));
            throw new CustomException("Something went wrong with creating user");
        }
    }

    @Override
    @Transactional
    public void updateUser(UserProfile user) {
        final String SQL_DETAILS = """
                update user_details set email = ?
                where sub = ?
                """;
        final String SQL_PROFILE = """
                update user_profile set family_name = ?, given_name = ?, picture = ?
                where sub = ?
                """;
        try {
            jdbc.update(SQL_DETAILS, user.getSub());
            jdbc.update(SQL_PROFILE, user.getSub());
        } catch (DataAccessException e) {
            log.error(String.format(
                    """
                            @method [void updateUser(UserDetails user)] ->
                            @data [user = %1$s ] ->
                            @error: %2$s
                            """, user, e
            ));
            throw new CustomException("Something went wrong with creating user");
        }
    }

    @Override
    public void deleteUser(String sub) {
        final String SQL = "delete from user_details where sub = ?";
        jdbc.update(SQL, sub);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails     ud      = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final String SQL = """
                update user_details set password = ?
                """;
        try {
            if (!ud.getPassword().equals(encoder.encode(oldPassword))) {
                throw new CustomException("Old Password is wrong");
            }
            jdbc.update(SQL, encoder.encode(ud.getPassword()));
        } catch (DataAccessException e) {
            log.error(String.format(
                    """
                            @method [changePassword(String oldPassword, String newPassword)] ->
                            @data [user = %1$s ] ->
                            @error: %2$s
                            """, ud, e
            ));
            throw new CustomException("Something went wrong with creating user");
        }
    }

    @Override
    public UserProfile loadUserByUsername(String username) {
        final String SQL = """
                select
                    ud.sub,
                    ud.email,
                    ud.password,
                    up.enabled,
                    up.family_name,
                    up.given_name,
                    up.picture
                from user_details ud
                left join user_profile up
                on ud.sub = up.sub
                where ud.email =  ?""";
        try {
            UserProfile userProfile = jdbc.queryForObject(SQL, (rs, rowNum) -> {
                // Set<Role> roles = urService.getRolesByUsername(username);
                Set<Role> roles = new HashSet<>();
                roles.add(Role.USER);
                roles.add(Role.CREATOR);
                List<? extends GrantedAuthority> authorities
                        = roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
                return new UserProfile(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        authorities,
                        rs.getString("SUB"),
                        rs.getString("FAMILY_NAME"),
                        rs.getString("GIVEN_NAME"),
                        rs.getString("PICTURE")
                );
            }, username);

            if (userProfile == null) {
                throw new UsernameNotFoundException("User cannot be found.");
            }
            return userProfile;
        } catch (DataAccessException e) {
            throw new CustomException("User cannot be found.");
        }
    }

    @Override
    public UserProfile loadUserBySub(String sub) {
        final String SQL = """
                select
                    ud.sub,
                    ud.email,
                    ud.password,
                    up.email_verified,
                    up.family_name,
                    up.given_name,
                    up.local,
                    up.picture
                from user_details ud
                left join user_profile up
                on ud.sub = up.sub
                where ud.sub = ?
                                """;
        try {
            return jdbc.queryForObject(SQL, (rs, rowNum) -> {
                if (rs.first()) {
                    Set<Role> roles = urService.getRolesBySub(sub);
                    List<? extends GrantedAuthority> authorities
                            = roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
                    return new UserProfile(
                            rs.getString("EMAIL"),
                            rs.getString("PASSWORD"),
                            authorities,
                            rs.getString("SUB"),
                            rs.getString("FAMILY_NAME"),
                            rs.getString("GIVEN_NAME"),
                            rs.getString("PICTURE")
                    );
                } else {
                    throw new UsernameNotFoundException("User cannot be found.");
                }
            }, sub);
        } catch (DataAccessException e) {
            throw new CustomException("User cannot be found.");
        }
    }
}
