package com.example.car_managment.problem.service;

import com.example.car_managment.employees.entites.Employee;
import com.example.car_managment.problem.entities.Problem;
import com.example.car_managment.problem.repo.ProblemRepository;
import com.example.car_managment.employees.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProblemService {

  @Autowired
  private ProblemRepository problemRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional
  public void reactToProblem(Long problemId) {
    Problem problem = problemRepository.findById(problemId)
      .orElseThrow(() -> new RuntimeException("Problem not found"));

    problem.setStatus(Problem.ProblemStatus.REACTED);
    problemRepository.save(problem);

    Employee employee = problem.getEmployee();
    // Optionnellement notifier l'employé
  }

  @Transactional
  public void declareProblem(Long employeeId, String description) {
    Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new RuntimeException("Employee not found"));

    Problem problem = new Problem();
    problem.setEmployee(employee);
    problem.setDescription(description);
    problem.setStatus(Problem.ProblemStatus.REPORTED);
    problemRepository.save(problem);
  }

  @Transactional(readOnly = true)
  public List<Problem> getProblemsForUser(Long userId) {
    return problemRepository.findByEmployeeId(userId);
  }
}
