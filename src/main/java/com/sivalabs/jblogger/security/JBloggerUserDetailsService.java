package com.sivalabs.jblogger.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.services.UserService;

import java.util.Optional;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class JBloggerUserDetailsService implements UserDetailsService
{
	private UserService userService;

	@Autowired
	public JBloggerUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Optional<User> user = userService.findUserByEmail(userName);
		if(user.isPresent()){
			return new AuthenticatedUser(user.get());
		} else {
			throw new UsernameNotFoundException("Email "+userName+" not found");
		}
	}

}
