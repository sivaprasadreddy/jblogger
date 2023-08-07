package com.sivalabs.jblogger.security;

import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Optional<User> user = userRepository.findByEmail(userName);
        if (user.isPresent()) {
            return new AuthenticatedUser(user.get());
        } else {
            throw new UsernameNotFoundException("Email " + userName + " not found");
        }
    }
}
