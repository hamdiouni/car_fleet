import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarFormComponent } from './car-form/car-form.component';
import { MaintenanceListComponent } from './maintenance-list/maintenance-list.component';
import { MaintenanceFormComponent } from './maintenance-form/maintenance-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AnalyticsComponent } from './analytics/analytics.component';
import { LoginComponent } from './login/login.component';
//import { RegisterComponent } from './register/register.component';
import { MaintenanceDetailComponent } from './maintenance-detail/maintenance-detail.component';
import { CarOwnerDashboardComponent } from './car-owner-dashboard/car-owner-dashboard.component';
import { LogisticsDashboardComponent } from './logistics-dashboard/logistics-dashboard.component';

const routes: Routes = [
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add-employee', component: EmployeeFormComponent },
  { path: 'edit-employee/:id', component: EmployeeFormComponent },

  { path: 'cars', component: CarListComponent },
  { path: 'add-car', component: CarFormComponent },
  { path: 'edit-car/:id', component: CarFormComponent },
  { path: 'login', component: LoginComponent },
  //{ path: 'register', component: RegisterComponent },


  { path: 'maintenances', component: MaintenanceListComponent },
  { path: 'add-maintenance', component: MaintenanceFormComponent },
  { path: 'edit-maintenance/:id', component: MaintenanceFormComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'analytics', component: AnalyticsComponent },

  { path: '', redirectTo: '/employees', pathMatch: 'full' },
  { path: 'maintenance-detail/:id', component: MaintenanceDetailComponent },
  {
    path: 'admin',
    component: DashboardComponent,


  },
  {
    path: 'car-owner',
    component: CarOwnerDashboardComponent,
  },
  {
    path: 'logistics',
    component: LogisticsDashboardComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
