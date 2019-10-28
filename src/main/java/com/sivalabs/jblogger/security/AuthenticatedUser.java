package com.sivalabs.jblogger.security;

import com.sivalabs.jblogger.entities.Role;
import com.sivalabs.jblogger.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{

	private static final long serialVersionUID = 1L;
	private User user;
	
	public AuthenticatedUser(User user)
	{
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}
	
	public User getUser()
	{
		return user;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
	{
		List<Role> roles = user.getRoles();
		final String[] roleNames = roles.stream().map(Role::getName).toArray(String[]::new);
		return AuthorityUtils.createAuthorityList(roleNames);
	}
}
