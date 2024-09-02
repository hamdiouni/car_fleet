package com.example.car_managment.problem.entities;

import com.example.car_managment.employees.entites.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "problems")
public class Problem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee; // Association avec l'entité Employee

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ProblemStatus status; // Statut du problème

  @Column(nullable = false)
  private String description; // Description du problème

  @Column(nullable = false)
  private LocalDateTime timestamp = LocalDateTime.now(); // Date de déclaration du problème

  public enum ProblemStatus {
    REPORTED,
    IN_PROGRESS,
    REACTED,
    RESOLVED
  }
}
