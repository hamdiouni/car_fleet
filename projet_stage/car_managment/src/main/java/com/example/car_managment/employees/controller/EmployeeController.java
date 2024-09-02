package com.example.car_managment.employees.controller;

import com.example.car_managment.employees.dto.EmployeeDTO;
import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  @Autowired
  private EmployeeService service;

  @GetMapping
  public List<EmployeeDTO> getAllEmployees() {
    return service.findAll();
  }

  @PostMapping
  public EmployeeDTO createEmployee(@RequestBody Employee employee) {
    return service.save(employee);
  }

  @PutMapping("/{id}")
  public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
    employee.setId(id);
    return service.save(employee);
  }

  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    service.deleteById(id);
  }

  @GetMapping("/{id}")
  public EmployeeDTO getEmployeeById(@PathVariable Long id) {
    return service.findById(id);
  }
}
