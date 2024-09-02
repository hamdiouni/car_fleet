package com.example.car_managment.cars.entities;

import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.maint.entities.Maintenance;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String licensePlate;
  private String model;
  private String status;

  @ManyToOne
  @JoinColumn(name = "employee_identity_card", referencedColumnName = "identityCardNumber")  // Link by identity card number
  private Employee employee;

  @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
  private List<Maintenance> maintenances;

  @Column(name = "leasing_contract", columnDefinition = "LONGBLOB")
  private byte[] leasingContract;

  @Column(name = "insurance", columnDefinition = "LONGBLOB")
  private byte[] insurance;

  @Column(name = "technical_visit", columnDefinition = "LONGBLOB")
  private byte[] technicalVisit;

  @Column(name = "technical_visit_date")
  private LocalDate technicalVisitDate;

  // Getters, Setters, Constructors
}
