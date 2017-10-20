package com.sivalabs.jblogger.security;

import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JBloggerUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    JBloggerUserDetailsService jBloggerUserDetailsService;

    @Test
    public void loadUserByUsername() throws Exception {
        String email = "admin@gmail.com";
        final User domainUser = new User();
        domainUser.setEmail(email);
        domainUser.setPassword("pwd");
        Optional<User> user = Optional.of(domainUser);

        when(userRepository.findByEmail(email)).thenReturn(user);

        final UserDetails userDetails = jBloggerUserDetailsService.loadUserByUsername(email);
        assertThat(userDetails).isNotNull();
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameThrowsUserNameNotFound() throws Exception {
        String email = "admin@gmail.com";

        when(userRepository.findByEmail(email)).thenThrow(new UsernameNotFoundException("User not found"));

        jBloggerUserDetailsService.loadUserByUsername(email);
    }
}
