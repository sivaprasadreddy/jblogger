/**
 * 
 */
package com.sivalabs.jblogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.sivalabs.jblogger.config.JBloggerSettings;

/**
 * @author Siva
 *
 */
@EnableCaching
@EnableConfigurationProperties({JBloggerSettings.class})
@SpringBootApplication
public class JBloggerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(JBloggerApplication.class, args);
	}

}
