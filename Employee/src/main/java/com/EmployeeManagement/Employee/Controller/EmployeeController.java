package com.EmployeeManagement.Employee.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.Employee.Model.AddEmployee;

import com.EmployeeManagement.Employee.Service.EmployeeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

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
    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<String> addEmployee(@RequestBody AddEmployee addEmployee) {
        String add = employeeServiceImpl.createEmployee(addEmployee);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @GetMapping("employees")
    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<List<AddEmployee>> getAllEmployee() {
        List<AddEmployee> list = employeeServiceImpl.getAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("employee/{id}")
    @Operation(summary = "Get an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<List<AddEmployee>> getEmployee(@PathVariable Long id) {
        List<AddEmployee> employee = employeeServiceImpl.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    @Operation(summary = "Update an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<String> updateEmpl(@PathVariable Long id, @RequestBody AddEmployee addEmployee) {
            String response = employeeServiceImpl.updateEmployee(id, addEmployee);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
        Boolean response = employeeServiceImpl.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
