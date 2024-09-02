package com.example.car_managment.employees.service;

import com.example.car_managment.employees.dto.EmployeeDTO;
import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.employees.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository repository;

  public List<EmployeeDTO> findAll() {
    return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  public EmployeeDTO save(Employee employee) {
    Employee savedEmployee = repository.save(employee);
    return convertToDTO(savedEmployee);
  }

  public EmployeeDTO findById(Long id) {
    Employee employee = repository.findById(id).orElse(null);
    return convertToDTO(employee);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  private EmployeeDTO convertToDTO(Employee employee) {
    if (employee == null) {
      return null;
    }
    EmployeeDTO dto = new EmployeeDTO();
    dto.setId(employee.getId());
    dto.setName(employee.getName());
    dto.setRole(employee.getRole());
    dto.setEmail(employee.getEmail());
    dto.setIdentityCardNumber(employee.getIdentityCardNumber());
    dto.setPhoneNumber(employee.getPhoneNumber());
    return dto;
  }
}
