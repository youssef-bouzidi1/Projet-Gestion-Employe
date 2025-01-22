// com.hrms.employee.repository.EmployeeRepository (Renamed to UserRepository)
package com.hrms.employee.repository;

import com.hrms.employee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}