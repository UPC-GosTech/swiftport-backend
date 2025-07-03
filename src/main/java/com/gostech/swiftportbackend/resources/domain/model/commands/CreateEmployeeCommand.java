package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreateEmployeeCommand(String name, String lastName, String employeeStatus, String email, String phoneNumber, Long positionId) {
    public CreateEmployeeCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (employeeStatus == null || employeeStatus.isEmpty()) {
            throw new IllegalArgumentException("employeeStatus cannot be null");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber cannot be null or empty");
        }
        if (positionId == null || positionId <= 0) {
            throw new IllegalArgumentException("positionId cannot be null or empty");
        }
    }
}
