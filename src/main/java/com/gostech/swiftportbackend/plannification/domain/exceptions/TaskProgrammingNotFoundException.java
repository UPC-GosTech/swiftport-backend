package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskProgrammingNotFoundException extends RuntimeException {
    public TaskProgrammingNotFoundException(Long taskProgrammingId) {
        super("Task programming with id " + taskProgrammingId + " not found");
    }
}
