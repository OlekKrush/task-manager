package com.pet.taskmanager.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserProfileDAO<T extends UserDetails> {

    void createUser(UserDetails user);

    /**
     * Update the specified user.
     */
    void updateUser(T user);

    /**
     * Remove the user with the given login name from the system.
     */
    void deleteUser(String username);

    /**
     * Modify the current user's password. This should change the user's password in the
     * persistent user repository (datbase, LDAP etc).
     * @param oldPassword current password (for re-authentication if required)
     * @param newPassword the password to change to
     */
    void changePassword(String oldPassword, String newPassword);

    T loadUserByUsername(String username);
    T loadUserBySub(String sub);
}
