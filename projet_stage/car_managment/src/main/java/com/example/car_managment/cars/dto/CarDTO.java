package com.example.car_managment.cars.dto;

import java.time.LocalDate;

public class CarDTO {
  private Long id;
  private String licensePlate;
  private String model;
  private String status;
  private String employeeIdentityCard;  // Link by identity card number
  private byte[] leasingContract;
  private byte[] insurance;
  private byte[] technicalVisit;
  private LocalDate technicalVisitDate;

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getEmployeeIdentityCard() {
    return employeeIdentityCard;
  }

  public void setEmployeeIdentityCard(String employeeIdentityCard) {
    this.employeeIdentityCard = employeeIdentityCard;
  }

  public byte[] getLeasingContract() {
    return leasingContract;
  }

  public void setLeasingContract(byte[] leasingContract) {
    this.leasingContract = leasingContract;
  }

  public byte[] getInsurance() {
    return insurance;
  }

  public void setInsurance(byte[] insurance) {
    this.insurance = insurance;
  }

  public byte[] getTechnicalVisit() {
    return technicalVisit;
  }

  public void setTechnicalVisit(byte[] technicalVisit) {
    this.technicalVisit = technicalVisit;
  }

  public LocalDate getTechnicalVisitDate() {
    return technicalVisitDate;
  }

  public void setTechnicalVisitDate(LocalDate technicalVisitDate) {
    this.technicalVisitDate = technicalVisitDate;
  }
}
