import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { HrDashboardComponent } from './components/hr-dashboard/hr-dashboard.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { DepartmentListComponent } from './components/department-list/department-list.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { PayslipListComponent } from './components/payslip-list/payslip-list.component';
import { PayslipDetailsComponent } from './components/payslip-details/payslip-details.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/hr/dashboard', pathMatch: 'full' },
  
  
  {
    path: 'hr',
    canActivate: [AuthGuard],
    data: { roles: ['HR'] },
    children: [
      { path: 'dashboard', component: HrDashboardComponent },
      { path: 'employees', component: EmployeeListComponent },
      { path: 'employees/new', component: EmployeeFormComponent },
      { path: 'employees/edit/:id', component: EmployeeFormComponent },
      { path: 'departments', component: DepartmentListComponent },
      { path: 'leave-requests', component: LeaveRequestComponent },
      { path: 'payslips', component: PayslipListComponent },
      { path: 'payslips/:id', component: PayslipDetailsComponent },
    ]
  },
  {
    path: 'employee',
    canActivate: [AuthGuard],
    data: { roles: ['EMPLOYEE'] },
    children: [
      { path: 'dashboard', component: EmployeeDashboardComponent },
      { path: 'payslips', component: PayslipListComponent },
      { path: 'payslips/:id', component: PayslipDetailsComponent },
      { path: 'leave-requests', component: LeaveRequestComponent },
      { path: 'profile', component: ProfileComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }