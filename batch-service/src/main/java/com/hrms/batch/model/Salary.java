// com.hrms.batch.model.Salary
package com.hrms.batch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    private LocalDate paymentDate;
    private Double baseSalary;
    private Double overtimePay;
    private Double bonuses;
    private Double deductions;
    private Double netSalary;

    @Transient // Add @Transient here
    private List<SalaryComponent> components;
}