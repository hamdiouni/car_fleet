package com.example.car_managment.maint.dto;

public class MaintenanceDTO {
  private Long id;
  private String scheduledDate;
  private String status;
  private String notes;
  private Long carId;



  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  private String licensePlate;  // Add this field

  public double getCout() {
    return cout;
  }

  public void setCout(float cout) {
    this.cout = cout;
  }

  private double cout;

  private String invoiceDate;
  private byte[] invoiceFile;  // Ajout du fichier PDF

  // Getters and Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getScheduledDate() {
    return scheduledDate;
  }

  public void setScheduledDate(String scheduledDate) {
    this.scheduledDate = scheduledDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long carId) {
    this.carId = carId;
  }

  


  public String getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(String invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public byte[] getInvoiceFile() {
    return invoiceFile;
  }

  public void setInvoiceFile(byte[] invoiceFile) {
    this.invoiceFile = invoiceFile;
  }
}
