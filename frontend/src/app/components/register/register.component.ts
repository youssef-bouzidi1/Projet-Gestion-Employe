// src/app/components/auth/register/register.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Department, DepartmentService } from '../../services/department.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  loading = false;
    departments: Department[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
      private departmentService: DepartmentService,
      private router: Router,
      private snackBar: MatSnackBar
  ) {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        username: ['', Validators.required],
      password: ['', Validators.required],
        position: ['', Validators.required],
        departmentId: ['', Validators.required]
    });
  }
  ngOnInit(): void {
      this.loadDepartments();
  }

    loadDepartments(): void {
        this.departmentService.getDepartments()
            .subscribe(departments => this.departments = departments);
    }

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.authService.register(this.registerForm.value).subscribe({
        next: (user) => {
            this.snackBar.open('Registration successful', 'Close', {
                duration: 3000
            });
            this.router.navigate(['/login']);
        },
        error: (error) => {
            this.snackBar.open('Registration failed: ' + error.message, 'Close', {
                duration: 3000
            });
            this.loading = false;
        }
    });
  }
}