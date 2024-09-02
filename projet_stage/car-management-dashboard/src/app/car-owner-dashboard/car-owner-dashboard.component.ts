import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../services/NotificationService';
import { ToastrService } from 'ngx-toastr';
import { ProblemService } from '../services/problem.service';
import { Problem, ProblemStatus } from '../problem';

@Component({
  selector: 'app-car-owner-dashboard',
  templateUrl: './car-owner-dashboard.component.html',
  styleUrls: ['./car-owner-dashboard.component.css']
})
export class CarOwnerDashboardComponent implements OnInit {
  notifications: any[] = [];
  problems: Problem[] = [];

  userId = 15; // Replace with actual user ID

  constructor(private notificationService: NotificationService, private toastr: ToastrService, private problemService: ProblemService) { }

  ngOnInit(): void {
    //this.loadNotifications();
    this.loadProblems();

  }
  loadProblems() {
    // Charge les problèmes en fonction de l'utilisateur, remplacez par une méthode appropriée si nécessaire
    this.problemService.getProblemsForUser(this.userId).subscribe(data => {
      this.problems = data;
      this.showProblemPopups(data);
    });
  }
  /*loadNotifications() {
    this.notificationService.getNotificationsForUser(this.userId).subscribe(data => {
      this.notifications = data;
      this.showNotificationPopups(data);
    });
  }*/

  declareProblem() {
    // Ici, vous pourriez avoir un formulaire ou une boîte de dialogue pour recueillir les détails du problème
    const problemDescription = prompt('Enter problem description:');
    if (problemDescription) {
      this.problemService.declareProblem(this.userId, problemDescription).subscribe(() => {
        this.toastr.success('Problem declared successfully!');
        this.loadProblems(); // Rafraîchit la liste des problèmes
      }, error => {
        this.toastr.error('Failed to declare the problem.');
      });
    }
  }

  showProblemPopups(problems: Problem[]) {
    problems.forEach(problem => {
      if (problem.status === ProblemStatus.REPORTED) {
        alert(`New Problem: ${problem.description}`);
      }
    });
  }

}
