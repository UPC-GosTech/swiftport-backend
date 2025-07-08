package com.gostech.swiftportbackend.execution.domain.exceptions;

public class ExecutionNotUpdatedException extends RuntimeException {
    public ExecutionNotUpdatedException(String message) {
        super("Execution could not be updated: " + message);
    }
}
