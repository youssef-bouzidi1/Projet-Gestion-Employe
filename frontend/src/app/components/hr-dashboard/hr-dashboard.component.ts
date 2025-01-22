import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { LeaveService } from '../../services/leave.service';

@Component({
  selector: 'app-hr-dashboard',
  templateUrl: './hr-dashboard.component.html',
  styleUrls: ['./hr-dashboard.component.css']
})
export class HrDashboardComponent implements OnInit {
  totalEmployees = 0;
  pendingLeaveRequests = 0;

  constructor(
    private employeeService: EmployeeService,
    private leaveService: LeaveService
  ) {}

  ngOnInit() {
    this.loadDashboardData();
  }

  private loadDashboardData() {
    this.employeeService.getEmployees().subscribe(employees => {
      this.totalEmployees = employees.length;
    });

    this.leaveService.getLeaves().subscribe(leaves => {
      this.pendingLeaveRequests = leaves.filter(l => l.status === 'PENDING').length;
    });
  }
}