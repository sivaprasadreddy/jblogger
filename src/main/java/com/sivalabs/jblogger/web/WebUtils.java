/**
 * 
 */
package com.sivalabs.jblogger.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivalabs.jblogger.entities.Post;

/**
 * @author Siva
 *
 */
public class WebUtils
{
	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
	
	public static final String TWITTER_SHARE_PATH = "https://twitter.com/intent/tweet?status=";
	public static final String FACEBOOK_SHARE_PATH = "https://www.facebook.com/sharer/sharer.php?";
	public static final String LINKEDIN_SHARE_PATH = "http://www.linkedin.com/shareArticle?mini=true&";
	
	private WebUtils() {}
	
	public static String getURLWithContextPath(HttpServletRequest request)
	{
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
	
	public static String getTwitterShareLink(HttpServletRequest request, Post post)
	{
		return TWITTER_SHARE_PATH +encode(post.getTitle())+" "+getURLWithContextPath(request)+"/posts/"+post.getUrl();
	}
	
	public static String getFacebookShareLink(HttpServletRequest request, Post post)
	{
		return FACEBOOK_SHARE_PATH +"u="+getURLWithContextPath(request)+"/posts/"+post.getUrl()+"&t="+encode(post.getTitle());
	}
	
	public static String getLinkedInShareLink(HttpServletRequest request, Post post)
	{
		return LINKEDIN_SHARE_PATH +"title="+encode(post.getTitle())+"&url="+getURLWithContextPath(request)+"/posts/"+post.getUrl();
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
