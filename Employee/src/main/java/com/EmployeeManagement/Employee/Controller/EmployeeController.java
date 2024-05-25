package com.EmployeeManagement.Employee.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.Employee.Model.AddEmployee;

import com.EmployeeManagement.Employee.Service.EmployeeServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("employee")
    public ResponseEntity<String> addEmployee(@RequestBody AddEmployee addEmployee) {
        String add = employeeServiceImpl.createEmployee(addEmployee);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @GetMapping("employees")
    public ResponseEntity<List<AddEmployee>> getAllEmployee() {
        List<AddEmployee> list = employeeServiceImpl.getAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("employee/{id}")
    public ResponseEntity<List<AddEmployee>> getEmployee(@PathVariable Long id) {
        List<AddEmployee> employee = employeeServiceImpl.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("update/{id}")
    public ResponseEntity<String> updateEmpl(@PathVariable Long id, @RequestBody AddEmployee addEmployee) {
        try {
            String response = employeeServiceImpl.updateEmployee(id, addEmployee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong."+e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
        Boolean response = employeeServiceImpl.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
