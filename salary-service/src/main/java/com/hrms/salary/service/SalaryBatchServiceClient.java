// com.hrms.salary.service.SalaryBatchServiceClient
package com.hrms.salary.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "batch-service", url = "http://localhost:8086/api") // Replace with your batch service URL
public interface SalaryBatchServiceClient {
    @PostMapping("/salaries/generate")
    void generateSalaries();
}