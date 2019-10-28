package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class EmailService
{
	private final ApplicationProperties applicationProperties;
	private final JavaMailSender javaMailSender;

	@Autowired
	public EmailService(ApplicationProperties applicationProperties, JavaMailSender javaMailSender) {
		this.applicationProperties = applicationProperties;
		this.javaMailSender = javaMailSender;
	}

	@Async
	public CompletableFuture<Void> send(String subject, String content)
	{
		String supportEmail = applicationProperties.getSupportEmail();
		
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
			log.error("", e);
		}
		return CompletableFuture.completedFuture(null);
	}
	
}
