/**
 * 
 */
package com.sivalabs.jblogger.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sivalabs.jblogger.core.services.PostService;
import com.sivalabs.jblogger.search.BlogSearch;

/**
 * @author Siva
 *
 */
@Component
public class JBloggerInitializer 
{
	private static final Logger logger = LoggerFactory.getLogger(JBloggerInitializer.class); 
	
	@Autowired BlogSearch blogSearch;
	@Autowired PostService postService;
	
	//ApplicationReadyEvent
	@EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
		logger.info("Received ContextRefreshedEvent "+event.getTimestamp());
		logger.info("Initializing JBlogger...........");
		blogSearch.index(postService.findAllPosts());
		logger.info("Done.");
    }
}
