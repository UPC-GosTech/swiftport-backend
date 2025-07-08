package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskNotSavedException extends RuntimeException {
    public TaskNotSavedException(String message) {
        super("Task could not be saved: " + message);
    }
}
