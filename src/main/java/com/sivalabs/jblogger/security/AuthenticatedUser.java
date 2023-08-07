package com.sivalabs.jblogger.security;

import com.sivalabs.jblogger.entities.User;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {
    private final User user;

    public AuthenticatedUser(User user) {
        super(user.getEmail(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toSet());
    }
}
