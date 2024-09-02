package com.example.car_managment.notification.controller;

import com.example.car_managment.notification.dto.NotificationDTO;
import com.example.car_managment.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  @Autowired
  private NotificationService notificationService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<NotificationDTO>> getNotificationsForUser(@PathVariable Long userId) {
    List<NotificationDTO> notifications = notificationService.getNotificationsForUser(userId);
    return new ResponseEntity<>(notifications, HttpStatus.OK);
  }

  @GetMapping("/stream/{userId}")
  public SseEmitter streamNotifications(@PathVariable Long userId) {
    SseEmitter emitter = new SseEmitter();
    notificationService.addEmitter(userId, emitter);
    return emitter;
  }

  /*@PostMapping("/react")
  public ResponseEntity<Void> reactToProblem(@RequestParam Long notificationId) {
    notificationService.reactToProblem(notificationId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/declare")
  public ResponseEntity<Void> declareProblem(@RequestParam Long employeeId, @RequestParam String description) {
    notificationService.declareProblem(employeeId, description);
    return new ResponseEntity<>(HttpStatus.OK);
  }*/
}
