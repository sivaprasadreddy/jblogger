/**
 * 
 */
package com.sivalabs.jblogger.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Siva
 *
 */
@Component
public class JBloggerInitializer 
{
	private static final Logger logger = LoggerFactory.getLogger(JBloggerInitializer.class); 
	
	@EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
		logger.info("Received ContextRefreshedEvent "+event.getTimestamp());
		logger.info("Initializing JBlogger...........");
		//TODO;
		logger.info("Done.");
    }
}
