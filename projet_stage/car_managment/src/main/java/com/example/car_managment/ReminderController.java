package com.example.car_managment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

  @Autowired
  private TechnicalVisitReminderService reminderService;

  @PostMapping("/test")
  public ResponseEntity<String> testReminder() {
    reminderService.checkTechnicalVisits(); // Appeler la méthode manuellement
    return ResponseEntity.ok("Rappels testés avec succès.");
  }
}

