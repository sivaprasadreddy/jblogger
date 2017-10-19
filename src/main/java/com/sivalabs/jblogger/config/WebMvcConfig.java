package com.sivalabs.jblogger.config;

import com.sivalabs.jblogger.web.interceptors.WebRequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

/**
 * @author Siva
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private WebRequestLogger webRequestLogger;
	
	@Override
	public Validator getValidator()
	{
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource);
		return factory;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		//registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(webRequestLogger);
	}
	
	@Bean
	public SpringSecurityDialect securityDialect()
	{
		return new SpringSecurityDialect();
	}

}
