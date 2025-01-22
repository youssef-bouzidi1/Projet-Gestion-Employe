// com.hrms.batch.jobs.SalaryItemProcessor
package com.hrms.batch.jobs;

import com.hrms.batch.model.Employee;
import com.hrms.batch.model.Salary;
import com.hrms.batch.service.SalaryServiceClient;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;


public class SalaryItemProcessor implements ItemProcessor<Employee, Salary> {
    @Autowired
    SalaryServiceClient salaryServiceClient;
    @Override
    public Salary process(Employee employee) {
        return salaryServiceClient.calculateSalary(employee.getId(), employee.getSalary());
    }
}