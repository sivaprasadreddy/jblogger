/**
 * 
 */
package com.sivalabs.jblogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Siva
 *
 */
@SpringBootApplication
@EnableCaching
public class JBloggerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(JBloggerApplication.class, args);
	}

}
