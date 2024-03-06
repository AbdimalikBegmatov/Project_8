package com.example.project_8_new.Services.Impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to,String subject,String body) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);

    }

}
