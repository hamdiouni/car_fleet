package com.example.car_managment.maint.controller;

import com.example.car_managment.AuthService;
import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.maint.dto.MaintenanceDTO;
import com.example.car_managment.maint.entities.Maintenance;
import com.example.car_managment.maint.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

  @Autowired
  private MaintenanceService service;

  @GetMapping
  public List<MaintenanceDTO> getAllMaintenances() {
    return service.findAll();
  }

  @PostMapping(consumes = {"multipart/form-data"})
  public MaintenanceDTO createMaintenance(@RequestPart("maintenance") Maintenance maintenance,
                                          @RequestPart("file") MultipartFile file,
                                          @RequestParam("licensePlate") String licensePlate) {
    return service.createMaintenanceWithCarPlate(maintenance, file, licensePlate);
  }

  @PutMapping("/{id}")
  public MaintenanceDTO updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
    maintenance.setId(id);
    return service.save(maintenance);
  }

  @DeleteMapping("/{id}")
  public void deleteMaintenance(@PathVariable Long id) {
    service.deleteById(id);
  }

  @GetMapping("/{id}")
  public MaintenanceDTO getMaintenanceById(@PathVariable Long id) {
    return service.findById(id);
  }

  @GetMapping("/{id}/file")
  public byte[] getMaintenanceFile(@PathVariable Long id) {
    return service.getFile(id);
  }

  @GetMapping("/total-cost")
  public ResponseEntity<Double> getTotalCost() {
    double totalCost = service.calculateTotalCost();
    return ResponseEntity.ok(totalCost);
  }

  @GetMapping("/cost/{licensePlate}")
  public ResponseEntity<Double> getCostByLicensePlate(@PathVariable String licensePlate) {
    double carCost = service.calculateCostByLicensePlate(licensePlate);
    return ResponseEntity.ok(carCost);
  }

  @Autowired
  private AuthService authService;

  @GetMapping("/admin")
  public ResponseEntity<List<MaintenanceDTO>> getAllMaintenanceForAdmin(@RequestParam String email, @RequestParam String identityCardNumber) {
    if (authService.authenticate(email, identityCardNumber, Employee.UserRole.ADMIN)) {
      return ResponseEntity.ok(service.findAll());
    }
    return ResponseEntity.status(403).build();
  }

  @GetMapping("/car-owner")
  public ResponseEntity<List<MaintenanceDTO>> getAllMaintenanceForCarOwner(@RequestParam String email, @RequestParam String identityCardNumber) {
    if (authService.authenticate(email, identityCardNumber, Employee.UserRole.CAR_OWNER)) {
      // Logic for car owner
      return ResponseEntity.ok(service.findAll());
    }
    return ResponseEntity.status(403).build();
  }

  @PostMapping("/logistics")
  public ResponseEntity<String> reactToProblem(@RequestParam String email, @RequestParam String identityCardNumber, @RequestBody Maintenance maintenance) {
    if (authService.authenticate(email, identityCardNumber, Employee.UserRole.LOGISTICS)) {
      // Logic for logistics employee to react to a problem
      return ResponseEntity.ok("Problem reacted to successfully.");
    }
    return ResponseEntity.status(403).build();
  }
}
