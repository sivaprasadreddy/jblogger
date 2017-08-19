package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.config.JBloggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author Siva
 *
 */
@Component
public class EmailService
{
	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private JBloggerConfig jBloggerConfig;
	

	@Autowired 
	private JavaMailSender javaMailSender;
	
    @Async
	public CompletableFuture<Void> send(String subject, String content)
	{
		String supportEmail = jBloggerConfig.getSupportEmail();
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(supportEmail);
        mailMessage.setReplyTo(supportEmail);
        mailMessage.setFrom(supportEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);      
		
        try
		{
			javaMailSender.send(mailMessage);
		} catch (MailException e)
		{
			logger.error("", e);
		}
		return CompletableFuture.completedFuture(null);
	}
	
}
