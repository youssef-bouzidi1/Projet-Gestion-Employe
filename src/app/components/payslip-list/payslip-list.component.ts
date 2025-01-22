import { Component, OnInit } from '@angular/core';
import { PayslipService } from '../../services/payslip.service';
import { Payslip } from '../../models/payslip.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-payslip-list',
  templateUrl: './payslip-list.component.html',
  styleUrls: ['./payslip-list.component.css']
})
export class PayslipListComponent implements OnInit {
  payslips: Payslip[] = [];
  displayedColumns: string[] = ['id', 'paymentDate', 'netSalary', 'actions'];
  loading: boolean = true;

  constructor(
    private payslipService: PayslipService,
    private snackBar: MatSnackBar,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.loadPayslips();
  }


    loadPayslips(): void {
        this.loading = true;
        console.log("Loading payslips...")
        const user = this.authService.currentUserValue;
        console.log("Current user:", user)
        if (user && user.role === 'EMPLOYEE') {
            const employeeId = user.id;
            this.payslipService.getPayslipByEmployeeId(employeeId).subscribe({
                next: (payslip) => {
                    this.payslips = [payslip];
                    console.log("Payslips received:", this.payslips);
                    this.loading = false;
                },
                error: (error) => {
                    this.loading = false;
                    this.snackBar.open('Error loading payslips: ' + error.message, 'Close', {
                        duration: 3000
                    });
                }
            });
        } else if (user && user.role === 'HR') {
            this.payslipService.getPayslips().subscribe({
                next: (payslips) => {
                    this.payslips = payslips;
                    console.log("Payslips received:", this.payslips);
                    this.loading = false;
                },
                error: (error) => {
                    this.loading = false;
                    this.snackBar.open('Error loading payslips: ' + error.message, 'Close', {
                        duration: 3000
                    });
                }
            });
        }
        else {
            this.loading = false;
            console.warn('User role not defined or recognized.');
            this.snackBar.open('Unable to load payslips.', 'Close', {
                duration: 3000
            });
        }
    }


  showPayslipDetails(id: number): void {
      const user = this.authService.currentUserValue;
    if (user && user.role === 'HR') {
      this.router.navigate(['/hr/payslips', id]);
    } else if (user && user.role === 'EMPLOYEE') {
      this.router.navigate(['/employee/payslips', id]);
    } else {
      console.warn('User role not defined or recognized.');
      this.snackBar.open('Unable to navigate to payslip details.', 'Close', {
        duration: 3000
      });
    }
  }

  generatePayslips(): void {
    this.payslipService.generatePayslips().subscribe({
      next: () => {
        this.snackBar.open('Payslips generated successfully', 'Close', {
          duration: 3000
        });
        this.loadPayslips();
      },
      error: (error) => {
        this.snackBar.open('Error: ' + error.message, 'Close', {
          duration: 3000
        });
      }
    });
  }
}