import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { CarService } from '../services/car.service';
import { MaintenanceService } from '../services/maintenance.service';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {
  visitorsData!: any[];
  globalSalesData!: any[];

  constructor(
    private employeeService: EmployeeService,
    private carService: CarService,
    private maintenanceService: MaintenanceService
  ) { }

  ngOnInit(): void {
    this.loadAnalyticsData();
  }

  loadAnalyticsData() {
    // Remplacez par des appels API réels pour récupérer les données
    this.visitorsData = [
      { label: 'Jan', oldVisitor: 5000, newVisitor: 8000, averageVisitor: 6500 },
      { label: 'Feb', oldVisitor: 4000, newVisitor: 7000, averageVisitor: 5500 },
      // Ajoutez plus de données selon les besoins
    ];

    this.globalSalesData = [
      { country: 'Germany', sales: 3562, average: 56.23 },
      { country: 'USA', sales: 2650, average: 25.23 },
      // Ajoutez plus de données selon les besoins
    ];
  }
  createVisitorsChart() {
    const ctx = document.getElementById('visitorsChart') as HTMLCanvasElement;
    new Chart(ctx, {
      type: 'line',
      data: {
        labels: this.visitorsData.map(data => data.label),
        datasets: [{
          label: 'Old Visitor',
          data: this.visitorsData.map(data => data.oldVisitor),
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1,
          fill: false
        }, {
          label: 'New Visitor',
          data: this.visitorsData.map(data => data.newVisitor),
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1,
          fill: false
        }, {
          label: 'Average Visitor',
          data: this.visitorsData.map(data => data.averageVisitor),
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1,
          fill: false
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}
