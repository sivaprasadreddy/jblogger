package com.sivalabs.jblogger;

import com.sivalabs.jblogger.config.ApplicationProperties;
import java.util.concurrent.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@EnableCaching
@EnableAsync
public class JBloggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JBloggerApplication.class, args);
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("JBlogger-");
        executor.initialize();
        return executor;
    }
}
