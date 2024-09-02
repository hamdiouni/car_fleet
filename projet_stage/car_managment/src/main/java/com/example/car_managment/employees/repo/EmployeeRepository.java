package com.example.car_managment.employees.repo;

import com.example.car_managment.employees.entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findByIdentityCardNumber(String identityCardNumber);
  Optional<Employee> findByEmail(String email);
  List<Employee> findByRole(Employee.UserRole role);


}
