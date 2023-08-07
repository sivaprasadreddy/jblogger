package com.sivalabs.jblogger.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.repositories.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class SecurityUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    SecurityUserDetailsService securityUserDetailsService;

    @Test
    void loadUserByUsername() {
        String email = "admin@gmail.com";
        final User domainUser = new User();
        domainUser.setEmail(email);
        domainUser.setPassword("pwd");
        Optional<User> user = Optional.of(domainUser);

        when(userRepository.findByEmail(email)).thenReturn(user);

        final UserDetails userDetails = securityUserDetailsService.loadUserByUsername(email);
        assertThat(userDetails).isNotNull();
    }

    @Test
    void loadUserByUsernameThrowsUserNameNotFound() throws Exception {
        String email = "admin@gmail.com";

        when(userRepository.findByEmail(email)).thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> {
            securityUserDetailsService.loadUserByUsername(email);
        });
    }
}
