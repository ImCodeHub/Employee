package com.EmployeeManagement.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagement.Employee.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
