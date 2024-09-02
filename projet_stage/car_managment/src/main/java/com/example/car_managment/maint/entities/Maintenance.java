package com.example.car_managment.maint.entities;

import com.example.car_managment.cars.entities.Car;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Maintenance implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date scheduledDate;
  private String status;
  private String notes;
  private double cout;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date invoiceDate;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;
  @Column(name = "invoice_file", columnDefinition = "LONGBLOB")
  private byte[] invoiceFile;

  // Getters, Setters, Constructors
}
