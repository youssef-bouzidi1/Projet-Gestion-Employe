// com.hrms.salary.service.SalaryCalculationService
package com.hrms.salary.service;

import com.hrms.salary.model.ComponentType;
import com.hrms.salary.model.Salary;
import com.hrms.salary.model.SalaryComponent;
import com.hrms.salary.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hrms.salary.service.SalaryBatchServiceClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryCalculationService {

    private final SalaryRepository salaryRepository;
    private final SalaryBatchServiceClient salaryBatchServiceClient;

    public Salary calculateSalary(Long employeeId, Double baseSalary) {
        Salary salary = new Salary();
        salary.setEmployeeId(employeeId);
        salary.setBaseSalary(baseSalary);
        salary.setPaymentDate(LocalDate.now());

        // Calculate overtime
        Double overtimePay = calculateOvertimePay(employeeId);
        salary.setOvertimePay(overtimePay);

        // Calculate bonuses
        Double bonuses = calculateBonuses(employeeId);
        salary.setBonuses(bonuses);

        // Calculate deductions
        Double deductions = calculateDeductions(employeeId);
        salary.setDeductions(deductions);

        // Calculate net salary
        Double netSalary = baseSalary + overtimePay + bonuses - deductions;
        salary.setNetSalary(netSalary);
        salary.setComponents(generateSalaryComponents(salary));
        salaryRepository.save(salary);
        return salary;
    }

    public List<Salary> getAllSalaries(){
        return salaryRepository.findAll();
    }

    public Salary getSalary(Long id){
        return salaryRepository.findById(id).orElse(null);
    }

    private List<SalaryComponent> generateSalaryComponents(Salary salary){
        List<SalaryComponent> components = new ArrayList<>();

        SalaryComponent base = new SalaryComponent();
        base.setAmount(salary.getBaseSalary());
        base.setType(ComponentType.BONUS);
        base.setName("Base Salary");
        base.setSalary(salary);
        components.add(base);

        SalaryComponent bonus = new SalaryComponent();
        bonus.setAmount(salary.getBonuses());
        bonus.setName("Bonus");
        bonus.setType(ComponentType.BONUS);
        bonus.setSalary(salary);
        components.add(bonus);

        SalaryComponent overtime = new SalaryComponent();
        overtime.setAmount(salary.getOvertimePay());
        overtime.setName("Overtime");
        overtime.setType(ComponentType.OVERTIME);
        overtime.setSalary(salary);
        components.add(overtime);

        SalaryComponent deductions = new SalaryComponent();
        deductions.setAmount(salary.getDeductions());
        deductions.setName("Deductions");
        deductions.setType(ComponentType.DEDUCTION);
        deductions.setSalary(salary);
        components.add(deductions);

        return components;
    }

    private Double calculateOvertimePay(Long employeeId) {
        // Implementation for overtime calculation
        return 100.0;
    }

    private Double calculateBonuses(Long employeeId) {
        // Implementation for bonus calculation
        return 50.0;
    }

    private Double calculateDeductions(Long employeeId) {
        // Implementation for deductions calculation
        return 20.0;
    }
    public void generateSalaries() {
        salaryBatchServiceClient.generateSalaries();
    }
}