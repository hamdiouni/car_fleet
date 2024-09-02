import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      identityCardNumber: ['', [Validators.required]],
      role: ['', [Validators.required]]
    });
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const { email, identityCardNumber, role } = this.loginForm.value;

      this.authService.checkUserRole(email, identityCardNumber, role).subscribe(
        hasRole => {
          if (hasRole) {
            // Rediriger en fonction du rôle
            this.router.navigate([this.redirectBasedOnRole(role)]);
          } else {
            this.toastr.error('You do not have the required role.');
          }
        },
        error => {
          console.error('Error checking role', error);
          this.toastr.error('An error occurred, please try again.');
        }
      );
    } else {
      this.toastr.warning('Please fill in all fields correctly.');
    }
  }

  redirectBasedOnRole(role: string): string {
    switch (role) {
      case 'ADMIN':
        return '/admin';
      case 'CAR_OWNER':
        return '/car-owner';
      case 'LOGISTICS':
        return '/logistics';
      default:
        return '/';
    }
  }
}
