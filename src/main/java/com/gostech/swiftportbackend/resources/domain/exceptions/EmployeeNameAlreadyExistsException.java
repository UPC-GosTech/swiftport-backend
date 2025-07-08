package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EmployeeNameAlreadyExistsException extends RuntimeException {
    public EmployeeNameAlreadyExistsException(String message1, String message2) {
        super("Employee with name already exists: " + message1 + " " + message2);
    }
}
