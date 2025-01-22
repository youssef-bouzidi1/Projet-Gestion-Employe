// com.hrms.batch.service.EmployeeServiceClient
package com.hrms.batch.service;

import com.hrms.batch.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="employee-service", url = "http://localhost:8081/api") // Replace with your employee service URL
public interface EmployeeServiceClient {
    @GetMapping("/auth/users")
    List<Employee> getAllEmployees();
}