package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task not found by id " + taskId);
    }
}
