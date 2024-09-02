import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarFormComponent } from './car-form/car-form.component';
import { MaintenanceListComponent } from './maintenance-list/maintenance-list.component';
import { MaintenanceFormComponent } from './maintenance-form/maintenance-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './footer/footer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { CardComponent } from './card/card.component';
import { ChartComponent } from './chart/chart.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MaintenanceDetailComponent } from './maintenance-detail/maintenance-detail.component';
import { CommonModule } from '@angular/common';  // Importer CommonModule
import { ToastrModule } from 'ngx-toastr';
import { CarOwnerDashboardComponent } from './car-owner-dashboard/car-owner-dashboard.component';
import { LogisticsDashboardComponent } from './logistics-dashboard/logistics-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthService } from './auth.service'; // Assurez-vous que le chemin est correct
import { LoginComponent } from '../app/login/login.component';

@NgModule({
    declarations: [
        AppComponent,
        EmployeeListComponent,
        EmployeeFormComponent,
        CarListComponent,
        CarFormComponent,
        MaintenanceDetailComponent,

        MaintenanceListComponent,
        MaintenanceFormComponent,
        FooterComponent,
        DashboardComponent,
        HeaderComponent,
        SidebarComponent, CardComponent, ChartComponent, NavbarComponent, CarOwnerDashboardComponent, LogisticsDashboardComponent,
        LoginComponent,

    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule, ReactiveFormsModule, CommonModule,
        ToastrModule.forRoot(),
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
