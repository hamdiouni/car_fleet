import { Component, OnInit } from '@angular/core';
import { ProblemService, } from '../services/problem.service';
import { ToastrService } from 'ngx-toastr';
import { Problem, ProblemStatus } from '../problem';

@Component({
  selector: 'app-logistics-dashboard',
  templateUrl: './logistics-dashboard.component.html',
  styleUrls: ['./logistics-dashboard.component.css']
})
export class LogisticsDashboardComponent implements OnInit {
  problems: Problem[] = [];
  userId = 15; // Remplacez par l'ID utilisateur réel

  constructor(private problemService: ProblemService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.loadProblems();
  }

  loadProblems() {
    // Charge les problèmes en fonction de l'utilisateur, remplacez par une méthode appropriée si nécessaire
    this.problemService.getProblemsForUser(this.userId).subscribe(data => {
      this.problems = data;
      this.showProblemPopups(data);
    });
  }

  reactToProblem(problemId: number) {
    this.problemService.reactToProblem(problemId).subscribe(() => {
      this.toastr.success('Problem reacted to successfully!');
      this.loadProblems(); // Rafraîchit la liste des problèmes
    }, error => {
      this.toastr.error('Failed to react to the problem.');
    });
  }

  showProblemPopups(problems: Problem[]) {
    problems.forEach(problem => {
      if (problem.status === ProblemStatus.REPORTED) {
        alert(`New Problem: ${problem.description}`);
      }
    });
  }
}
