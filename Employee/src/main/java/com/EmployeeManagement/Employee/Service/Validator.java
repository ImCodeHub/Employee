package com.EmployeeManagement.Employee.Service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.Employee.Entity.Employee;
import com.EmployeeManagement.Employee.Repository.EmployeeRepository;

// very important to keep Service annotation for validator class. 
@Service
public class Validator {

    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+$";
        boolean isValid = email.matches(emailRegex);
        logger.debug("Email validation for {} : {}", email, isValid);
        return isValid;
    }

    public boolean isEmailUnique(String email) {
        Optional<Employee> existEmail = employeeRepository.findByEmail(email);
        boolean isUnique = existEmail.isEmpty();
        logger.debug("Email Uniqueness check for {} : {}", email, isUnique);
        return isUnique;

    }
}
