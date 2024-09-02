package com.example.car_managment.maint.repo;

import com.example.car_managment.maint.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
  // Method to calculate the total maintenance cost
  List<Maintenance> findByCarId(Long carId);

  @Query("SELECT SUM(m.cout) FROM Maintenance m")
  Double sumAllMaintenanceCosts();

  // Method to calculate the maintenance cost by license plate
  @Query("SELECT SUM(m.cout) FROM Maintenance m WHERE m.car.licensePlate = :licensePlate")
  Double sumMaintenanceCostByLicensePlate(String licensePlate);}
