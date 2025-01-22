package com.hrms.batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@Data
@Entity
public class Employee {
    @Id
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private String role;
    @JsonProperty("contractType")
    private String contractType;
    @JsonProperty("joiningDate")
    private LocalDate joiningDate;
    @JsonProperty("position")
    private String position;
    @JsonProperty("salary")
    private Double salary;
    @JsonProperty("departmentId")
    private Long departmentId;
}