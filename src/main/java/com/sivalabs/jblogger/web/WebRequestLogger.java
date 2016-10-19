/**
 * 
 */
package com.sivalabs.jblogger.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Siva
 *
 */
@Component
public class WebRequestLogger extends HandlerInterceptorAdapter
{
	private static final String USER_CONTEXT_KEY = "UserContextKey";
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
		
		String mdcUserContextKey = "["+request.getRemoteHost()+":"+request.getSession().getId()+"]";
		MDC.put(USER_CONTEXT_KEY, mdcUserContextKey);
        
        Calendar cal = Calendar.getInstance();
        String url = request.getRequestURI();
        String referrer = request.getHeader("referer");
		logger.debug("Incoming Request: "+cal.getTime());
		logger.debug("URL: "+url);
		logger.debug("Referrer: "+referrer);
		return true;
    }
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	        ModelAndView modelAndView) throws Exception
	{
		super.postHandle(request, response, handler, modelAndView);
		MDC.remove(USER_CONTEXT_KEY);
	}
}
