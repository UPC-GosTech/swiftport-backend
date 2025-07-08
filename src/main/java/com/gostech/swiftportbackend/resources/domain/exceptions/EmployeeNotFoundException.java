package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long employeeId) {
        super("Employee with id " + employeeId + " does not exist");
    }
}
