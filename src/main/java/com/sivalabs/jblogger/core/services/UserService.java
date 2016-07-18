/**
 * 
 */
package com.sivalabs.jblogger.core.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.JBloggerException;
import com.sivalabs.jblogger.core.entities.Role;
import com.sivalabs.jblogger.core.entities.User;
import com.sivalabs.jblogger.core.repositories.RoleRepository;
import com.sivalabs.jblogger.core.repositories.UserRepository;

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
	
	public User getUserById(Integer id)
	{
		return userRepository.findOne(id);
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
					persistedRoles.add(roleRepository.findOne(role.getId()));
				}
			}
		}
		user.setRoles(persistedRoles);
		
		return userRepository.save(user);
	}
	
	public User updateUser(User user)
	{
		User persistedUser = getUserById(user.getId());
		if(persistedUser == null){
			throw new JBloggerException("User "+user.getId()+" doesn't exist");
		}
		
		List<Role> updatedRoles = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if(roles != null){
			for (Role role : roles) {
				if(role.getId() != null)
				{
					updatedRoles.add(roleRepository.findOne(role.getId()));
				}
			}
		}
		persistedUser.setRoles(updatedRoles);
		return userRepository.save(persistedUser);
	}
	
}
