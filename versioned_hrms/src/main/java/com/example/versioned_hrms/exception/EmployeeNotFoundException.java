package com.example.versioned_hrms.exception;


public class EmployeeNotFoundException
        extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Employee with id " + id + " not found");
    }
}
