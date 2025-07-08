package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskNotUpdatedException extends RuntimeException {
    public TaskNotUpdatedException(String message) {
        super("Error updating task description: " + message);
    }
}
