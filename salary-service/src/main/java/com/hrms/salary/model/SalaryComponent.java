// com.hrms.salary.model.SalaryComponent
package com.hrms.salary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name="salary_components")
public class SalaryComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ComponentType type;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "salary_id")
    @JsonIgnore
    private Salary salary;
}