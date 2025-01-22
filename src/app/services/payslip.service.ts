import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payslip } from '../models/payslip.model';

@Injectable({
  providedIn: 'root'
})
export class PayslipService {
  private apiUrl = 'http://localhost:8080/api/salaries';

  constructor(private http: HttpClient) { }

  getPayslips(): Observable<Payslip[]> {
    return this.http.get<Payslip[]>(this.apiUrl);
  }

  getPayslipsByEmployeeId(employeeId: number): Observable<Payslip[]> {
      return this.http.get<Payslip[]>(`${this.apiUrl}/${employeeId}`);
  }

    getPayslipByEmployeeId(employeeId: number): Observable<Payslip> {
        return this.http.get<Payslip>(`${this.apiUrl}/${employeeId}`);
    }
    getPayslip(id: number): Observable<Payslip> {
        return this.http.get<Payslip>(`${this.apiUrl}/${id}`);
    }
    generatePayslips(): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/generate`, {});
    }
}