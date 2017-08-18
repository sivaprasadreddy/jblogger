package com.sivalabs.jblogger.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.sivalabs.jblogger.config.JBloggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jblogger.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Siva
 *
 */
@Component
public class WebUtils
{
	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

	private static JBloggerConfig jBloggerConfig;

	@Autowired
	public WebUtils(JBloggerConfig config) {
		WebUtils.jBloggerConfig = config;
	}
	
	public static String getURLWithContextPath(HttpServletRequest request)
	{
		return request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort()
				+ request.getContextPath();
	}
	
	public static String getTwitterShareLink(HttpServletRequest request, Post post)
	{
		return jBloggerConfig.getTwitterShareUrl() +encode(post.getTitle())+" "+getURLWithContextPath(request)+"/posts/"+post.getUrl();
	}
	
	public static String getFacebookShareLink(HttpServletRequest request, Post post)
	{
		return jBloggerConfig.getFacebookShareUrl() +"u="+getURLWithContextPath(request)+"/posts/"+post.getUrl()+"&t="+encode(post.getTitle());
	}
	
	public static String getLinkedInShareLink(HttpServletRequest request, Post post)
	{
		return jBloggerConfig.getLinkedinShareUrl() +"title="+encode(post.getTitle())+"&url="+getURLWithContextPath(request)+"/posts/"+post.getUrl();
	}
	
	private static String encode(String str)
	{
		try
		{
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage(),e);
			return null;
		}
	}
}
