package com.EmployeeManagement.Employee.Exception;

public class CustomException {
    public static class EmployeeNotFoundException extends RuntimeException{
        // this is constructor below.
        public EmployeeNotFoundException(String message){
            super(message);
        }
    }  
    
    public static class InvalidEmailException extends RuntimeException{
        public InvalidEmailException(String message){
            super(message);
        }
    }

    public static class EmailAlreadyExistException extends RuntimeException{
        public EmailAlreadyExistException(String message){
            super(message);
        }
    }
}
