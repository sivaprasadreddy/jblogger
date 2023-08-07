package com.sivalabs.jblogger.services;

import com.sivalabs.jblogger.config.ApplicationProperties;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailService {
    private final ApplicationProperties properties;
    private final JavaMailSender javaMailSender;

    public EmailService(ApplicationProperties properties, JavaMailSender javaMailSender) {
        this.properties = properties;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public CompletableFuture<Void> send(String subject, String content) {
        String supportEmail = properties.getSupportEmail();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(supportEmail);
        mailMessage.setReplyTo(supportEmail);
        mailMessage.setFrom(supportEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            log.error("", e);
        }
        return CompletableFuture.completedFuture(null);
    }
}
