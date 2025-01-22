export interface Payslip {
    id: number;
    employeeId: number;
    paymentDate: string;
    baseSalary: number;
    overtimePay: number;
    bonuses: number;
    deductions: number;
    netSalary: number;
    components: PayslipComponent[];
}

export interface PayslipComponent {
    name: string;
    amount: number;
}