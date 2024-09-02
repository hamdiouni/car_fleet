package com.example.car_managment.problem.dto;

import java.time.LocalDateTime;

public class ProblemDTO {

  private Long id;
  private Long employeeId; // ID de l'employé lié au problème
  private ProblemStatus status;
  private String description; // Description du problème
  private LocalDateTime timestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public ProblemStatus getStatus() {
    return status;
  }

  public void setStatus(ProblemStatus status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public enum ProblemStatus {
    REPORTED,
    IN_PROGRESS,
    REACTED,
    RESOLVED
  }
}
