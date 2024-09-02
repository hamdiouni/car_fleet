package com.example.car_managment.notification.entities;
import com.example.car_managment.employees.entites.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long recipientId; // This should match the repository method

  private String message;

  @Column(name = "is_read", nullable = false)
  private boolean isRead;

  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  // getters and setters
}

