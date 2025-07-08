package com.gostech.swiftportbackend.execution.domain.exceptions;

public class ExecutionNoFoundException extends RuntimeException {
    public ExecutionNoFoundException(Long executionId) {
        super("Execution with id " + executionId + " not found");
    }
}
