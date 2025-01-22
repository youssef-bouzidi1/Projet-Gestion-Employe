// com.hrms.batch.service.SalaryServiceClient
package com.hrms.batch.service;

import com.hrms.batch.model.Salary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="salary-service", url = "http://localhost:8083/api") // Replace with your salary service URL
public interface SalaryServiceClient {
    @GetMapping("/salaries/calculate/{employeeId}/{baseSalary}")
    Salary calculateSalary(@PathVariable("employeeId") Long employeeId, @PathVariable("baseSalary") Double baseSalary);
}