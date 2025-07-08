package com.gostech.swiftportbackend.execution.domain.exceptions;

public class ExecutionNotSavedException extends RuntimeException {
    public ExecutionNotSavedException(String message) {
        super("Execution could not be saved: " + message);
    }
}
