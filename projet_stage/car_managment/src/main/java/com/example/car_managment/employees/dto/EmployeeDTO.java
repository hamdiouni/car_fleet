package com.example.car_managment.employees.dto;

import com.example.car_managment.employees.entites.Employee;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
  @Getter
  private Long id;



  @Getter
  private String name;








  @Enumerated(EnumType.STRING)
  private Employee.UserRole role;
  private String email;


  public void setPhoneNumber(String phoneNumber) {
   this.PhoneNumber = phoneNumber;
  }

  public void setIdentityCardNumber(String identityCardNumber) {
    this.identityCardNumber = identityCardNumber;
  }

  private String PhoneNumber;



  private String identityCardNumber;
  public enum UserRole {
    ADMIN,
    CAR_OWNER,
    LOGISTICS
  }
  // Getters and Setters
}
