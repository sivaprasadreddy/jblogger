/**
 * 
 */
package com.sivalabs.jblogger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Siva
 *
 */
@ConfigurationProperties(prefix="jblogger")
public class JBloggerSettings 
{
	private int postsPerPage;
	private String supportEmail;

	public int getPostsPerPage() {
		return postsPerPage;
	}

	public void setPostsPerPage(int postsPerPage) {
		this.postsPerPage = postsPerPage;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
}
