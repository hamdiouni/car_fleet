package com.example.car_managment;

import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.maint.dto.MaintenanceDTO;
import com.example.car_managment.maint.entities.Maintenance;
import com.example.car_managment.maint.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private MaintenanceService maintenanceService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    boolean authenticated = authService.authenticate(
      loginRequest.getEmail(),
      loginRequest.getIdentityCardNumber(),
      loginRequest.getRole()
    );

    if (authenticated) {
      return ResponseEntity.ok("Login successful");
    }

    return ResponseEntity.status(401).body("Invalid credentials");
  }

  @GetMapping("/maintenance")
  public ResponseEntity<List<MaintenanceDTO>> getMaintenance(@RequestParam String email, @RequestParam String identityCardNumber, @RequestParam String role) {
    Employee.UserRole userRole;

    try {
      userRole = Employee.UserRole.valueOf(role);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(null);
    }

    if (authService.authenticate(email, identityCardNumber, userRole)) {
      return ResponseEntity.ok(maintenanceService.findAll());
    }

    return ResponseEntity.status(403).build();
  }

  @PostMapping("/react")
  public ResponseEntity<String> reactToProblem(@RequestParam String email, @RequestParam String identityCardNumber, @RequestBody Maintenance maintenance) {
    if (authService.authenticate(email, identityCardNumber, Employee.UserRole.LOGISTICS)) {
      // Perform the problem reaction logic here
      return ResponseEntity.ok("Problem reacted to successfully.");
    }

    return ResponseEntity.status(403).build();
  }

  @GetMapping("/check-role")
  public ResponseEntity<Boolean> checkUserRole(@RequestParam String email,
                                               @RequestParam String identityCardNumber,
                                               @RequestParam String role) {
    try {
      Employee.UserRole userRole = Employee.UserRole.valueOf(role);
      boolean hasRole = authService.authenticate(email, identityCardNumber, userRole);
      return ResponseEntity.ok(hasRole);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(false);
    }
  }
}
