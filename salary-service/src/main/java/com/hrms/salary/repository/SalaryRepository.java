// com.hrms.salary.repository.SalaryRepository
package com.hrms.salary.repository;

import com.hrms.salary.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}