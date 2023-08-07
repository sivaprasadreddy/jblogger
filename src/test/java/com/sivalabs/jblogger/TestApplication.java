package com.sivalabs.jblogger;

import org.springframework.boot.SpringApplication;

public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.from(JBloggerApplication::main)
                .with(TestcontainersConfig.class)
                .run(args);
    }
}
