package com.EmployeeManagement.Employee.Model;

import lombok.Data;

@Data
public class AddEmployee {
    private String firstName;

    private String lastName;

    private String email;

    private String department;
}
