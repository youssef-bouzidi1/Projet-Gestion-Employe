import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';

import { AppRoutingModule } from './app-routing.module';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { HrDashboardComponent } from './components/hr-dashboard/hr-dashboard.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';
import { DepartmentListComponent } from './components/department-list/department-list.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DepartmentFormComponent } from './components/department-form/department-form.component';
import { PayslipListComponent } from './components/payslip-list/payslip-list.component';
import { PayslipDetailsComponent } from './components/payslip-details/payslip-details.component';


@NgModule({
  declarations: [
    AppComponent,
    // ... other declarations (make sure components declared here only use modules imported below)
    LoginComponent,
    RegisterComponent,
    HrDashboardComponent,
    EmployeeListComponent,
    EmployeeFormComponent,
    DepartmentListComponent,
    LeaveRequestComponent,
    EmployeeDashboardComponent,
    ProfileComponent,
    DepartmentFormComponent,
    PayslipListComponent,
    PayslipDetailsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule, // Add RouterModule to imports

    // Angular Material Modules
    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatTableModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatProgressBarModule,

    // Application Routing
    AppRoutingModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }