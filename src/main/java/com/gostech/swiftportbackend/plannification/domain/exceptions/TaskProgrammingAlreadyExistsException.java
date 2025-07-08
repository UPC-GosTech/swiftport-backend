package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskProgrammingAlreadyExistsException extends RuntimeException {
    public TaskProgrammingAlreadyExistsException(Long taskProgrammingId) {
        super("Task Programming %s already exists".formatted(taskProgrammingId));
    }
}
