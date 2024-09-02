package com.example.car_managment.notification.service;

import com.example.car_managment.email.EmailService1;
import com.example.car_managment.notification.dto.NotificationDTO;
import com.example.car_managment.notification.entities.Notification;
import com.example.car_managment.notification.repo.NotificationRepository;
import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.employees.repo.EmployeeRepository;
import com.example.car_managment.problem.entities.Problem;
import com.example.car_managment.problem.repo.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private ProblemRepository problemRepository;

  private final ConcurrentHashMap<Long, CopyOnWriteArrayList<SseEmitter>> emitters = new ConcurrentHashMap<>();

  @Autowired
  private EmailService1 emailService;

 /* @Transactional
  public void reactToProblem(Long notificationId) {
    Notification notification = notificationRepository.findById(notificationId)
      .orElseThrow(() -> new RuntimeException("Notification not found"));

    // Mark the notification as read
    notification.setRead(true);
    notificationRepository.save(notification);

    // Retrieve the associated problem
    Problem problem = problemRepository.findById(notification.getId())
      .orElseThrow(() -> new RuntimeException("Problem not found"));

    // Optionally update problem status or other related fields
    problem.setDescription(Problem.ProblemStatus.REACTED);
    problemRepository.save(problem);

    // Retrieve the employee who reported the problem
    Employee employee = problem.getEmployee();

    // Notify the employee
    sendReactionNotification(employee, problem);
  }

  @Transactional
  public void declareProblem(Long employeeId, String description) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new RuntimeException("Employee not found"));

    Problem problem = new Problem();
    problem.setEmployee(employee);
    problem.setDescription(Problem.ProblemStatus.valueOf(description));
    problemRepository.save(problem);

    // Optionally, create a notification for the problem if needed
  }*/

  private void sendReactionNotification(Employee employee, Problem problem) {
    String subject = "Your problem has been reacted to";
    String body = String.format("Dear %s,\n\nYour reported problem with ID %d has been reacted to.\n\nDescription: %s\n\nBest regards,\nYour Team",
      employee.getName(), problem.getId(), problem.getDescription());

    emailService.sendEmail(employee.getEmail(), subject, body);
  }

  public void addEmitter(Long userId, SseEmitter emitter) {
    emitters.computeIfAbsent(userId, k -> new CopyOnWriteArrayList<>()).add(emitter);
    emitter.onCompletion(() -> emitters.get(userId).remove(emitter));
  }

  public void sendNotification(String message, Long userId) {
    Employee user = employeeRepository.findById(userId)
      .orElseThrow(() -> new RuntimeException("User not found"));

    Notification notification = new Notification();
    notification.setMessage(message);
    notification.setTimestamp(new Date());
    notification.setRead(false);
    notification.setEmployee(user);
    notificationRepository.save(notification);

    NotificationDTO notificationDTO = new NotificationDTO();
    notificationDTO.setId(notification.getId());
    notificationDTO.setMessage(notification.getMessage());
    notificationDTO.setRead(notification.isRead());
    notificationDTO.setTimestamp(notification.getTimestamp());
    notificationDTO.setEmployeeId(notification.getEmployee().getId());

    if (emitters.containsKey(userId)) {
      for (SseEmitter emitter : emitters.get(userId)) {
        try {
          emitter.send(SseEmitter.event().name("notification").data(notificationDTO));
        } catch (IOException e) {
          emitters.get(userId).remove(emitter);
        }
      }
    }
  }

  public List<NotificationDTO> getNotificationsForUser(Long userId) {
    List<Notification> notifications = notificationRepository.findByEmployeeId(userId);
    return notifications.stream()
      .map(notification -> {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.isRead());
        dto.setTimestamp(notification.getTimestamp());
        dto.setEmployeeId(notification.getEmployee().getId());
        return dto;
      })
      .toList();
  }
}
