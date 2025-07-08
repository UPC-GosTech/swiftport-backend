package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class TaskTitleAlreadyExistsException extends RuntimeException {
    public TaskTitleAlreadyExistsException() {
        super("Task Title already exists");
    }
}
