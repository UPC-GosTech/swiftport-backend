package com.gostech.swiftportbackend.notifications.infrastructure.services;

import com.gostech.swiftportbackend.notifications.application.internal.outboundservices.email.SmtpNotificationSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpNotificationSenderImpl implements SmtpNotificationSender {
    
    private final JavaMailSender mailSender;
    
    @Value("${spring.mail.username:default@example.com}")
    private String fromEmail;
    
    public SmtpNotificationSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            mailSender.send(message);
            
            System.out.println("Email enviado exitosamente a: " + to);
            return true;
        } catch (Exception e) {
            System.err.println("Error enviando email: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean sendEmail(String[] to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            mailSender.send(message);
            
            System.out.println("Email enviado exitosamente a múltiples destinatarios: " + String.join(", ", to));
            return true;
        } catch (Exception e) {
            System.err.println("Error enviando email a múltiples destinatarios: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true indica que es HTML
            
            mailSender.send(message);
            
            System.out.println("Email HTML enviado exitosamente a: " + to);
            return true;
        } catch (MessagingException e) {
            System.err.println("Error enviando email HTML: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean sendHtmlEmail(String[] to, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true indica que es HTML
            
            mailSender.send(message);
            
            System.out.println("Email HTML enviado exitosamente a múltiples destinatarios: " + String.join(", ", to));
            return true;
        } catch (MessagingException e) {
            System.err.println("Error enviando email HTML a múltiples destinatarios: " + e.getMessage());
            return false;
        }
    }
} 