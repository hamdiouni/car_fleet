import { Component, OnInit } from '@angular/core';
import { MaintenanceService } from '../services/maintenance.service';
import { NotificationService } from '../services/NotificationService';
import { AuthService } from '../auth.service'; // Assurez-vous que le chemin est correct
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  animations: [
    trigger('flyInOut', [
      state('in', style({ opacity: 1, transform: 'translateX(0)' })),
      transition('void => *', [
        style({ opacity: 0, transform: 'translateX(-100%)' }),
        animate('300ms ease-in')
      ]),
      transition('* => void', [
        animate('300ms ease-out', style({ opacity: 0, transform: 'translateX(100%)' }))
      ])
    ])
  ]
})
export class DashboardComponent implements OnInit {
  totalCost = 0;
  carCost = 0;
  licensePlate = '';
  notifications: any[] = [];
  email = 'hamdi.ouni@esprit.tn';
  identityCardNumber = '15017635';
  role = 'ADMIN'; // Exemple de rôle

  constructor(
    private maintenanceService: MaintenanceService,
    private notificationService: NotificationService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadTotalCost();
    this.loadNotifications();
    this.checkRole();
  }

  checkRole() {
    this.authService.checkUserRole(this.email, this.identityCardNumber, this.role).subscribe(
      hasRole => {
        if (hasRole) {
          this.toastr.success('Role verified successfully!');
        } else {
          this.toastr.error('Role verification failed.');
        }
      },
      error => {
        console.error('Error checking role', error);
        this.toastr.error('An error occurred while checking the role.');
      }
    );
  }

  loadTotalCost() {
    this.maintenanceService.getTotalCost().subscribe(total => {
      this.totalCost = total;
    });
  }

  searchByLicensePlate() {
    if (this.licensePlate) {
      this.maintenanceService.getCostByLicensePlate(this.licensePlate).subscribe(cout => {
        this.carCost = cout;
      });
    }
  }

  loadNotifications() {
    const adminId = 8;
    this.notificationService.getNotificationsForUser(adminId).subscribe(notifications => {
      this.notifications = notifications;
      this.showNotificationPopups(notifications);
    });
  }

  showSuccess(message: string) {
    this.toastr.success(message, 'Notification');
  }

  showNotificationPopups(notifications: any[]) {
    notifications.forEach(notification => {
      if (!notification.isRead) {
        this.showSuccess(notification.message);
      }
    });
  }
}
