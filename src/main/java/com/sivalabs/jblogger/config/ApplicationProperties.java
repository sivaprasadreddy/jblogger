package com.sivalabs.jblogger.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {
    private int postsPerPage;
    private String supportEmail;
    private String twitterShareUrl;
    private String facebookShareUrl;
    private String linkedinShareUrl;
}
