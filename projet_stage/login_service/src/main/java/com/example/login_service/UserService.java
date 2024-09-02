package com.example.login_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  private TokenService tokenService;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public User signup(User user) throws MessagingException {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setVerified(false);
    User savedUser = userRepository.save(user);

    String token = tokenService.generateToken(savedUser.getId());
    emailService.sendVerificationEmail(user.getEmail(), token);

    return savedUser;
  }

  public boolean verifyEmail(String token) {
    UUID userId = tokenService.verifyToken(token);
    if (userId != null) {
      User user = userRepository.findById(userId).orElse(null);
      if (user != null) {
        user.setVerified(true);
        userRepository.save(user);
        return true;
      }
    }
    return false;
  }

  public boolean resetPassword(String email) throws MessagingException {
    User user = userRepository.findByEmail(email);
    if (user != null) {
      String token = tokenService.generateToken(user.getId());
      emailService.sendPasswordResetEmail(email, token);
      return true;
    }
    return false;
  }

  public boolean updatePassword(String token, String newPassword) {
    UUID userId = tokenService.verifyToken(token);
    if (userId != null) {
      User user = userRepository.findById(userId).orElse(null);
      if (user != null) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
      }
    }
    return false;
  }
}
