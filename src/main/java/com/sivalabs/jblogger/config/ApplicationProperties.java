package com.sivalabs.jblogger.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix="application")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class ApplicationProperties
{
	private int postsPerPage;
	private String supportEmail;
	private String twitterShareUrl;
	private String facebookShareUrl;
	private String linkedinShareUrl;

}
