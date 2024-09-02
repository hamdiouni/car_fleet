package com.example.car_managment;

import com.example.car_managment.employees.entites.Employee;

public class LoginRequest {
  private String email;
  private String identityCardNumber;

  public void setRole(Employee.UserRole role) {
    this.role = role;
  }

  private Employee.UserRole role;

  // Getters et Setters

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIdentityCardNumber() {
    return identityCardNumber;
  }

  public void setIdentityCardNumber(String identityCardNumber) {
    this.identityCardNumber = identityCardNumber;
  }

  public Employee.UserRole getRole() {
    return role;
  }


  public enum UserRole {
    ADMIN,
    CAR_OWNER,
    LOGISTICS
  }
}

