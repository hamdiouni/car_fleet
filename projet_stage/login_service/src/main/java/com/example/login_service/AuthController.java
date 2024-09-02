package com.example.login_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody User user) throws MessagingException {
    User newUser = userService.signup(user);
    return ResponseEntity.ok(newUser);
  }

  @GetMapping("/verify")
  public ResponseEntity<?> verifyEmail(@RequestParam String token) {
    boolean verified = userService.verifyEmail(token);
    return verified ? ResponseEntity.ok("Email verified successfully") : ResponseEntity.badRequest().body("Invalid token");
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
    boolean success = userService.resetPassword(email);
    return success ? ResponseEntity.ok("Password reset email sent") : ResponseEntity.badRequest().body("Email not found");
  }

  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
    boolean success = userService.updatePassword(token, newPassword);
    return success ? ResponseEntity.ok("Password updated successfully") : ResponseEntity.badRequest().body("Invalid token");
  }
}
