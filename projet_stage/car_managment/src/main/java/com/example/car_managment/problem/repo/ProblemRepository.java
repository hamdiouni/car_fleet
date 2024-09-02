package com.example.car_managment.problem.repo;

import com.example.car_managment.problem.entities.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

  List<Problem> findByEmployeeId(Long employeeId);
}
