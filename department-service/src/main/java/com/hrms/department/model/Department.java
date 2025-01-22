package com.hrms.department.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "parent_department_id")
    private Long parentDepartmentId; // Store the parent department ID

    // Consider how you want to handle sub-departments without direct entity dependency
    // You might fetch them via an API call when needed.
    // @OneToMany(mappedBy = "parentDepartment")
    // private List<Department> subDepartments;

    @Column(name = "manager_id") // Store the manager ID
    private Long managerId;
}