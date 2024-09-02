package com.example.car_managment;

import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.employees.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public boolean authenticate(String email, String identityCardNumber, Employee.UserRole requiredRole) {
    Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);

    if (employeeOptional.isPresent()) {
      Employee employee = employeeOptional.get();
      return employee.getIdentityCardNumber().equals(identityCardNumber) && employee.getRole().equals(requiredRole);
    }

    return false;
  }
}
