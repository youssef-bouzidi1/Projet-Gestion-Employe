import { Component, OnInit } from '@angular/core';
import { LeaveService } from '../../services/leave.service';
import { PayslipService } from '../../services/payslip.service';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css']
})
export class EmployeeDashboardComponent implements OnInit {
  recentLeaveRequests: any[] = [];
  recentPayslips: any[] = [];

  constructor(
    private leaveService: LeaveService,
    private payslipService: PayslipService
  ) {}

  ngOnInit() {
    this.loadDashboardData();
  }

  private loadDashboardData() {
    this.leaveService.getLeaves().subscribe(leaves => {
      this.recentLeaveRequests = leaves.slice(0, 5);
    });

    this.payslipService.getPayslips().subscribe(payslips => {
      this.recentPayslips = payslips.slice(0, 5);
    });
  }
}