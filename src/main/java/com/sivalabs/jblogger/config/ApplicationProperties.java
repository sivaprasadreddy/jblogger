package com.sivalabs.jblogger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private int postsPerPage;
    private String supportEmail;
    private String twitterShareUrl;
    private String facebookShareUrl;
    private String linkedinShareUrl;
}
