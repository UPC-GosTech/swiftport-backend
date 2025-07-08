package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskProgrammingNotUpdatedException extends RuntimeException {
    public TaskProgrammingNotUpdatedException(String message) {
        super("Error updating task programming: " + message);
    }
}
