package com.EmployeeManagement.Employee.Exception;

public class CustomerException {
    public class EmployeeNotFoundException extends RuntimeException{
        // this is constructor below.
        public EmployeeNotFoundException(String message){
            super(message);
        }
    }  
    
    public class InvalidEmailException extends RuntimeException{
        public InvalidEmailException(String message){
            super(message);
        }
    }

    public class EmailAlreadyExistException extends RuntimeException{
        public EmailAlreadyExistException(String message){
            super(message);
        }
    }
}
