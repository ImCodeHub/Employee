package com.EmployeeManagement.Employee.Service;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.Employee.Entity.Employee;
import com.EmployeeManagement.Employee.Exception.CustomException.*;
import com.EmployeeManagement.Employee.Model.AddEmployee;
import com.EmployeeManagement.Employee.Repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // logger
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Validator validate;

    // add the employee in DB.
    @Override
    public String createEmployee(AddEmployee addEmployee) {

        Employee employee = new Employee();
        if (addEmployee != null) {
            String email = addEmployee.getEmail();
            if (!validate.isValidEmail(email)) {
                logger.warn("Invalid Email formate: {}",email);
                throw new InvalidEmailException("Email is not valid.");
            }
            if (!validate.isEmailUnique(email)) {
                logger.warn("Email already Exist: {}", email);
                throw new EmailAlreadyExistException( "Email is already Exist. Kindly change the email id.");
            }
            employee.setFirstName(addEmployee.getFirstName());
            employee.setLastName(addEmployee.getLastName());
            employee.setEmail(addEmployee.getEmail());
            employee.setDepartment(addEmployee.getDepartment());
            employeeRepository.save(employee);

            logger.info("Employee details saved for email : {}", email);
            return "employee details saved.";
        }
        logger.error("AddEmployee Object is null ");
        throw new RuntimeException( "somthing went wrong unable to save the details.");
    }

    // Retrive all the Employee list.
    @Override
    public List<AddEmployee> getAllEmployees() {
        List<AddEmployee> employeeList = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        if (employees != null) {
            for (Employee employee : employees) {
                AddEmployee addEmployee = new AddEmployee();

                addEmployee.setFirstName(employee.getFirstName());
                addEmployee.setLastName(employee.getLastName());
                addEmployee.setEmail(employee.getEmail());
                addEmployee.setDepartment(employee.getDepartment());

                // logger.info("Retrieve the data: {}", addEmployee);
                employeeList.add(addEmployee);
            }
            logger.info("Employee data is retrieve.");
            return employeeList;
        } else {
            logger.error("all Employee data is null");
            return null;
        }
    }

    // Retrive the Emoloyee by ID.
    @Override
    public List<AddEmployee> getEmployeeById(Long id) {
        List<AddEmployee> responseList = new ArrayList<>();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            AddEmployee addEmployee = new AddEmployee();

            addEmployee.setFirstName(employee.getFirstName());
            addEmployee.setLastName(employee.getLastName());
            addEmployee.setEmail(employee.getEmail());
            addEmployee.setDepartment(employee.getDepartment());

            responseList.add(addEmployee);

            logger.info("Employee Found By this ID :- {}",addEmployee);
            return responseList;
        } else {
            logger.error("Employee not found by this id:- {}",id);
            throw new EmployeeNotFoundException("Employee not found with id: "+id);
        }
    }

    // Update the Employee from DB.
    @Override
    public String updateEmployee(Long id, AddEmployee addEmployee) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            String email = addEmployee.getEmail();
            // validate the email.
            if (!validate.isValidEmail(email)) {
                logger.warn("Invalid E-mail formate: {}", email);
                throw new InvalidEmailException("Email is not valid.");
            }

            // validate whether email id Unique.
            if (!validate.isEmailUnique(email)) {
                logger.warn("E-mail Id is already Exist: {}", email);
                throw new EmailAlreadyExistException( "Email is already Exist. Kindly change the email id.");
            }
            employee.setFirstName(addEmployee.getFirstName());
            employee.setLastName(addEmployee.getLastName());
            employee.setEmail(email);
            employee.setDepartment(addEmployee.getDepartment());

            employeeRepository.save(employee);

            logger.info("Employee details updated for this email: {}", email);
            return "Employee Details have been updated successfully!";

        } else {
            logger.error("Employee details not found by this ID :{}", id);
            throw new EmployeeNotFoundException("Employee not found with id: "+id);
        }
    }

    // Delete the employee
    @Override
    public Boolean deleteEmployee(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            logger.info("Employee Data deleted for this ID : {}", id);
            employeeRepository.deleteById(id);
            return true;
        } else {
            logger.error("Employee not found by this ID:{}", id);
            throw new EmployeeNotFoundException("Employee not found with id: "+id);
        }
    }
}
