package com.sivalabs.jblogger.web;

import com.sivalabs.jblogger.config.ApplicationProperties;
import com.sivalabs.jblogger.domain.PostDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WebUtils {
    private static ApplicationProperties applicationProperties;

    @Autowired
    WebUtils(ApplicationProperties config) {
        WebUtils.applicationProperties = config;
    }

    public static String getTwitterShareLink(PostDTO post) {
        return applicationProperties.getTwitterShareUrl() + encode(post.getTitle()) + " " + getURLWithContextPath()
                + "/" + post.getUrl();
    }

    public static String getFacebookShareLink(PostDTO post) {
        return applicationProperties.getFacebookShareUrl() + "u=" + getURLWithContextPath() + "/" + post.getUrl()
                + "&t=" + encode(post.getTitle());
    }

    public static String getLinkedInShareLink(PostDTO post) {
        return applicationProperties.getLinkedinShareUrl() + "title=" + encode(post.getTitle()) + "&url="
                + getURLWithContextPath() + "/" + post.getUrl();
    }

    private static String getURLWithContextPath() {
        return "http://localhost:8080/";
    }

    private static String getURLWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    }

    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
