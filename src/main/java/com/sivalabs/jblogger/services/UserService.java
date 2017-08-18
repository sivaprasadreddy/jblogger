package com.sivalabs.jblogger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.JBloggerException;
import com.sivalabs.jblogger.entities.Role;
import com.sivalabs.jblogger.entities.User;
import com.sivalabs.jblogger.repositories.RoleRepository;
import com.sivalabs.jblogger.repositories.UserRepository;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class UserService 
{
	
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	
	public User login(String email, String password)
	{
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public User findUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> getUserById(Integer id)
	{
		return userRepository.findById(id);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user)
	{
		User userByEmail = findUserByEmail(user.getEmail());
		if(userByEmail != null){
			throw new JBloggerException("Email "+user.getEmail()+" already in use");
		}
		List<Role> persistedRoles = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if(roles != null){
			for (Role role : roles) {
				if(role.getId() != null)
				{
					persistedRoles.add(roleRepository.getOne(role.getId()));
				}
			}
		}
		user.setRoles(persistedRoles);
		
		return userRepository.save(user);
	}
	
	public User updateUser(User user)
	{
		Optional<User> persistedUser = getUserById(user.getId());
		if(!persistedUser.isPresent()){
			throw new JBloggerException("User "+user.getId()+" doesn't exist");
		}
		
		List<Role> updatedRoles = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if(roles != null){
			for (Role role : roles) {
				if(role.getId() != null)
				{
					updatedRoles.add(roleRepository.getOne(role.getId()));
				}
			}
		}
		persistedUser.get().setRoles(updatedRoles);
		return userRepository.save(persistedUser.get());
	}
	
}
