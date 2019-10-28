package com.sivalabs.jblogger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
@Order(101)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/", "/forgotPwd","/resetPwd").permitAll()
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.permitAll()
                .and()
            .exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(userDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
}
