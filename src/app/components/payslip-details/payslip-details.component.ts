import { Component, OnInit } from '@angular/core';
import { PayslipService } from '../../services/payslip.service';
import { Payslip } from '../../models/payslip.model';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-payslip-details',
  templateUrl: './payslip-details.component.html',
  styleUrls: ['./payslip-details.component.css']
})
export class PayslipDetailsComponent implements OnInit {
  payslip: Payslip | null = null;
  isHR: boolean = false;

  constructor(
    private payslipService: PayslipService,
    private route: ActivatedRoute,
      private authService:AuthService
  ) { }

  ngOnInit(): void {
    this.isHR = this.authService.isHR();
    this.loadPayslipDetails();
  }

  loadPayslipDetails(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.payslipService.getPayslip(id)
      .subscribe(payslip => this.payslip = payslip);
  }
}