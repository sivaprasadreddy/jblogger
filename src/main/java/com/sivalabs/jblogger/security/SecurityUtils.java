package com.sivalabs.jblogger.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Siva
 *
 */
public class SecurityUtils
{
	private SecurityUtils() {
	}

	public static AuthenticatedUser getCurrentUser() {

	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof AuthenticatedUser) {
	    	return (AuthenticatedUser) principal;
	    }
	    return null;
	}

	public static boolean isLoggedIn() {
	    return getCurrentUser() != null;
	}
}
