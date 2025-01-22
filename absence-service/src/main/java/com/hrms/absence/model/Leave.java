package com.hrms.absence.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType type;
    private LeaveStatus status;
    private String reason;
    
    @Column(name = "approver_id")
    private Long approverId;
}

enum LeaveType {
    VACATION, SICK, PERSONAL, MATERNITY, PATERNITY
}

