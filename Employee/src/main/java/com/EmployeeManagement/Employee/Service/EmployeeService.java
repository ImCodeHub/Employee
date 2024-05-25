package com.EmployeeManagement.Employee.Service;

import java.util.*;

import com.EmployeeManagement.Employee.Model.AddEmployee;

public interface EmployeeService{
    public String createEmployee(AddEmployee addEmployee);
    public List<AddEmployee> getAllEmployees();
    public List<AddEmployee> getEmployeeById(Long id);
    public String updateEmployee(Long id , AddEmployee addEmployee);
    public Boolean deleteEmployee(Long id);
}
