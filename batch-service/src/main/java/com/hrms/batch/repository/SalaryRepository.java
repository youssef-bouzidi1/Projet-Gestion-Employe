// com.hrms.batch.repository.SalaryRepository
package com.hrms.batch.repository;

import com.hrms.batch.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}