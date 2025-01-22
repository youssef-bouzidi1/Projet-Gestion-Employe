// com.hrms.salary.controller.SalaryController
package com.hrms.salary.controller;

import com.hrms.salary.model.Salary;
import com.hrms.salary.service.SalaryCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryCalculationService salaryCalculationService;

    @GetMapping("/calculate/{employeeId}/{baseSalary}")
    public Salary calculateSalary(@PathVariable Long employeeId, @PathVariable Double baseSalary){
        return salaryCalculationService.calculateSalary(employeeId, baseSalary);
    }

    @PostMapping("/generate")
    public ResponseEntity<Void> generateSalaries() {
        salaryCalculationService.generateSalaries();
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Salary> getAllSalaries() {
        return salaryCalculationService.getAllSalaries();
    }

    @GetMapping("/{id}")
    public Salary getSalary(@PathVariable Long id){
        return salaryCalculationService.getSalary(id);
    }
}