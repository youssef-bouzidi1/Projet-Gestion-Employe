import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profileForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private employeeService: EmployeeService,
    private snackBar: MatSnackBar
  ) {
    this.profileForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: [''],
      address: ['']
    });
  }

  ngOnInit() {
    // In a real app, get the current employee's ID from an auth service
    const employeeId = 1;
    this.employeeService.getEmployee(employeeId).subscribe(employee => {
      this.profileForm.patchValue(employee);
    });
  }

  onSubmit() {
    if (this.profileForm.valid) {
      const employeeId = 1; // In a real app, get from auth service
      this.employeeService.updateEmployee(employeeId, this.profileForm.value)
        .subscribe(() => {
          this.snackBar.open('Profile updated successfully', 'Close', {
            duration: 3000
          });
        });
    }
  }
}