// com.hrms.batch.controller.SalaryController
package com.hrms.batch.controller;

import com.hrms.batch.service.SalaryCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryCalculationService salaryCalculationService;

    @PostMapping("/generate")
    public ResponseEntity<Void> generateSalaries() {
        salaryCalculationService.generateSalaries();
        return ResponseEntity.ok().build();
    }
}