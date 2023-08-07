package com.sivalabs.jblogger.security;

import com.sivalabs.jblogger.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return userRepository
                .findByEmail(userName)
                .map(AuthenticatedUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
    }
}
