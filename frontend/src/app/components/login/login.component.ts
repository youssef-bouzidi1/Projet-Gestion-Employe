// src/app/components/auth/login/login.component.ts
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {
    // redirect to home if already logged in
    if (this.authService.currentUserValue) {
      this.router.navigate(['/']);
    }

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  ngOnInit() {}

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authService.login(
      this.loginForm.get('username')?.value,
      this.loginForm.get('password')?.value
    ).subscribe({
      next: (user) => {
        if (user) {
          const redirectUrl = user.role === 'HR' ? '/hr/dashboard' : '/employee/dashboard';
          this.router.navigate([redirectUrl]);
        } else {
            this.snackBar.open('Login failed: Invalid credentials.', 'Close', {
                duration: 3000
            });
            this.loading = false;
        }
      },
      error: (error) => {
          this.snackBar.open('Login failed: ' + error.message, 'Close', {
              duration: 3000
          });
        this.loading = false;
      }
    });
  }
}