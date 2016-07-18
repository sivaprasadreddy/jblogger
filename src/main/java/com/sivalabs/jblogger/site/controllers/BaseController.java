/**
 * 
 */
package com.sivalabs.jblogger.site.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sivalabs.jblogger.core.entities.Blog;
import com.sivalabs.jblogger.core.entities.Tag;
import com.sivalabs.jblogger.core.services.BlogService;
import com.sivalabs.jblogger.core.services.EmailService;
import com.sivalabs.jblogger.core.services.TagService;
import com.sivalabs.jblogger.security.AuthenticatedUser;
import com.sivalabs.jblogger.security.SecurityUtils;

/**
 * @author Siva
 *
 */
public abstract class BaseController
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired protected ApplicationEventPublisher publisher;
	@Autowired protected MessageSource messageSource;
	@Autowired protected BlogService blogService;
	@Autowired protected TagService tagService;
	@Autowired protected EmailService emailService;
	
	public String getMessage(String code)
	{
		return messageSource.getMessage(code, null, null);
	}
	
	public String getMessage(String code, String defaultMsg)
	{
		return messageSource.getMessage(code, null, defaultMsg, null);
	}
	
	public void publish(Object event){
		publisher.publishEvent(event);
	}
	
	@ModelAttribute("authenticatedUser")
    public AuthenticatedUser authenticatedUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser)
    {
        return authenticatedUser;
    }
	
	public static AuthenticatedUser getCurrentUser() {
	    return SecurityUtils.getCurrentUser();
	}

	public static boolean isLoggedIn() {
	    return SecurityUtils.isLoggedIn();
	}
	
	@ModelAttribute("tagPostsCountMap")
	public Map<Tag, Integer> tagPostsCountMap() {
	    return tagService.getTagsWithCount();
	}
	
	@ModelAttribute("blogInfo")
	public Blog blogInfo() {
		return this.blogService.findBlogById(1);
	}
}
