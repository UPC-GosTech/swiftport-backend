package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EmployeeNotSavedException extends RuntimeException {
    public EmployeeNotSavedException(String message) {
        super("Employee could not be saved: " + message);
    }
}
