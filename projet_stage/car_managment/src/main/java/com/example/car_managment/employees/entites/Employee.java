package com.example.car_managment.employees.entites;

import com.example.car_managment.cars.entities.Car;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  @Enumerated(EnumType.STRING)
  private UserRole role;
  private String email;
  private String PhoneNumber;




  @Column(unique = true)
  private String identityCardNumber;  // New field for identity card number

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
  private List<Car> cars;

  public enum UserRole {
    ADMIN,
    CAR_OWNER,
    LOGISTICS
  }

    // Getters, Setters, Constructors
}
