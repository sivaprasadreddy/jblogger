package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class UserService 
{
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<User> findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

}
