package com.example.car_managment.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender emailSender;

  public void sendEmail(String to, String subject, String text, boolean isHtml) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true); // `true` indicates multipart
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setFrom("ounihamdi4@gmail.com"); // Votre adresse e-mail

      if (isHtml) {
        helper.setText(text, true); // `true` indicates HTML
      } else {
        helper.setText(text);
      }

      emailSender.send(message);
    } catch (MessagingException e) {
      e.printStackTrace(); // Handle the exception appropriately
    }
  }
}
