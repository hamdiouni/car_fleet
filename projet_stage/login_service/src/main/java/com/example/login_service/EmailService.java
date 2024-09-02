package com.example.login_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendVerificationEmail(String to, String token) throws MessagingException {
    String subject = "Email Verification";
    String body = "Please verify your email using this token: " + token;
    sendEmail(to, subject, body);
  }

  public void sendPasswordResetEmail(String to, String token) throws MessagingException {
    String subject = "Password Reset";
    String body = "Please reset your password using this token: " + token;
    sendEmail(to, subject, body);
  }

  private void sendEmail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    mailSender.send(message);
  }
}
