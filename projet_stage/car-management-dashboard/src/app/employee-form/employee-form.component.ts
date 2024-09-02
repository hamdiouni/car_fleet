import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {
  employeeForm: FormGroup;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.employeeForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      role: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      identityCardNumber: ['', Validators.required],
      phoneNumber: ['', Validators.required]  // Ajout du champ
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.employeeService.getEmployee(+id).subscribe(data => {
        this.employeeForm.patchValue(data); // Patch des valeurs dans le formulaire
      });
    }
  }

  saveEmployee(): void {
    if (this.employeeForm.valid) {
      const employee = this.employeeForm.value; // Récupérer la valeur du formulaire
      if (employee.id) {
        this.employeeService.updateEmployee(employee.id, employee).subscribe(() => {
          this.router.navigate(['/employees']);
        });
      } else {
        this.employeeService.createEmployee(employee).subscribe(() => {
          this.router.navigate(['/employees']);
        });
      }
    }
  }
}
