package com.sivalabs.jblogger.security;

import static org.assertj.core.api.Assertions.assertThat;

import com.sivalabs.jblogger.entities.Role;
import com.sivalabs.jblogger.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AuthenticatedUserTest {

    @Test
    void hasNoAuthorities() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("pwd");

        final AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
        assertThat(authenticatedUser.getUsername()).isEqualTo("admin@gmail.com");
        assertThat(authenticatedUser.getPassword()).isEqualTo("pwd");
        assertThat(authenticatedUser.getAuthorities()).hasSize(0);
    }

    @Test
    void hasAuthorities() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("pwd");
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        Role role2 = new Role();
        role2.setName("ROLE_USER");
        roles.add(role1);
        roles.add(role2);
        user.setRoles(roles);

        final AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
        assertThat(authenticatedUser.getUsername()).isEqualTo("admin@gmail.com");
        assertThat(authenticatedUser.getPassword()).isEqualTo("pwd");
        assertThat(authenticatedUser.getAuthorities()).hasSize(2);
    }
}
