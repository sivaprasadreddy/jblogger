package com.sivalabs.jblogger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Siva
 *
 */
@Component
@ConfigurationProperties(prefix="jblogger")
@Data
public class JBloggerConfig
{
	private int postsPerPage;
	private String supportEmail;
	private String twitterShareUrl;
	private String facebookShareUrl;
	private String linkedinShareUrl;

}
