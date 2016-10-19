/**
 * 
 */
package com.sivalabs.jblogger.config;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.sivalabs.jblogger.core.web.WebRequestLogger;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.SessionListener;

/**
 * @author Siva
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
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
		super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("login");
		registry.addRedirectViewController("/", "/posts");

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		super.addInterceptors(registry);
		registry.addInterceptor(webRequestLogger);
	}
	
	@Bean
	public SpringSecurityDialect securityDialect()
	{
		return new SpringSecurityDialect();
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return new EmbeddedServletContainerCustomizer() {
	            @Override
	            public void customize(ConfigurableEmbeddedServletContainer container) {
	                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/page-not-found"));
	                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/serverError"));
	            }
	        };
	}
	
	@Bean
    public FilterRegistrationBean javamelodyFilterBean() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(javamelodyFilter());
        registration.addUrlPatterns("/*");
        //registration.addInitParameter("paramName", "paramValue");
        registration.setName("javamelodyFilter");
        registration.setAsyncSupported(true);
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        return registration;
    }

    @Bean(name = "javamelodyFilter")
    public Filter javamelodyFilter() {
        return new MonitoringFilter();
    }
    
    @Bean(name = "javamelodysessionListener")
    public SessionListener sessionListener() {
        return new SessionListener();
    }
}
