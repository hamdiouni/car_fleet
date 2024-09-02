import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Employee } from '../employee';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[] = [];
  selectedEmployee: Employee | null = null;
  employeeForm: FormGroup;

  constructor(private employeeService: EmployeeService, private fb: FormBuilder) {
    this.employeeForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      role: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      identityCardNumber: ['', Validators.required],
      phoneNumber: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.employeeService.getEmployees().subscribe({
      next: (data) => this.employees = data,
      error: (err) => console.error('Failed to load employees', err)
    });
  }

  deleteEmployee(id: number): void {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cet employé ?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => this.loadEmployees(),
        error: (err) => console.error('Failed to delete employee', err)
      });
    }
  }

  editEmployee(employee: Employee): void {
    if (window.confirm('Êtes-vous sûr de vouloir modifier cet employé ?')) {
      this.selectedEmployee = employee;
      this.employeeForm.patchValue(employee);
    }
  }

  updateEmployee(): void {
    if (this.employeeForm.valid) {
      this.employeeService.updateEmployee(this.employeeForm.value.id, this.employeeForm.value).subscribe({
        next: () => {
          this.loadEmployees();
          this.selectedEmployee = null;
        },
        error: (err) => console.error('Failed to update employee', err)
      });
    }
  }

  cancelUpdate(): void {
    this.selectedEmployee = null;
    this.employeeForm.reset();
  }
}
