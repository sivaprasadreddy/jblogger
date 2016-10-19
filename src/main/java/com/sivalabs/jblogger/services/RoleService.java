/**
 * 
 */
package com.sivalabs.jblogger.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.JBloggerException;
import com.sivalabs.jblogger.entities.Role;
import com.sivalabs.jblogger.repositories.RoleRepository;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class RoleService
{
	@Autowired RoleRepository roleRepository;
	
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public Role getRoleById(Integer id) {
		return roleRepository.findOne(id);
	}
	
	public Role getRoleByName(String roleName)
	{
		return roleRepository.findByName(roleName);
	}
	
	public Role createRole(Role role)
	{
		Role roleByName = getRoleByName(role.getName());
		if(roleByName != null){
			throw new JBloggerException("Role "+role.getName()+" already exist");
		}
		
		return roleRepository.save(role);
	}
	
	public Role updateRole(Role role)
	{
		Role persistedRole = getRoleById(role.getId());
		if(persistedRole == null){
			throw new JBloggerException("Role "+role.getId()+" doesn't exist");
		}
		persistedRole.setDescription(role.getDescription());
		return roleRepository.save(persistedRole);
	}
	
	

}
