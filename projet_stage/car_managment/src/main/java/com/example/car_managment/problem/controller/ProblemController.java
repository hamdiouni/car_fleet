package com.example.car_managment.problem.controller;

import com.example.car_managment.problem.entities.Problem;
import com.example.car_managment.problem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

  @Autowired
  private ProblemService problemService;

  @PostMapping("/react")
  public ResponseEntity<Void> reactToProblem(@RequestParam Long problemId) {
    problemService.reactToProblem(problemId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/declare")
  public ResponseEntity<Void> declareProblem(@RequestParam Long employeeId, @RequestParam String description) {
    problemService.declareProblem(employeeId, description);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Problem>> getProblemsForUser(@PathVariable Long userId) {
    List<Problem> problems = problemService.getProblemsForUser(userId);
    return new ResponseEntity<>(problems, HttpStatus.OK);
  }
}
