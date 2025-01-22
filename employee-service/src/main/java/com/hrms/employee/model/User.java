package com.hrms.employee.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users") // Renamed table to users
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    private LocalDate joiningDate;
    private String position;
    private Double salary;

    @Column(name = "department_id") // Store the department ID
    private Long departmentId;
}

