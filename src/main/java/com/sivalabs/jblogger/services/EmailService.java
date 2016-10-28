/**
 * 
 */
package com.sivalabs.jblogger.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sivalabs.jblogger.config.JBloggerSettings;

/**
 * @author Siva
 *
 */
@Component
public class EmailService
{
	private Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private JBloggerSettings jbloggerSettings;
	

	@Autowired 
	private JavaMailSender javaMailSender;
	
    
	public void send(String subject, String content)
	{
		String supportEmail = jbloggerSettings.getSupportEmail();
		
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
	}
	
}
