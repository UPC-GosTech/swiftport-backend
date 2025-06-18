package com.gostech.swiftportbackend.execution.domain.model.commands;

public record AddModificationReasonToExecutionCommand(Long executionId, String reason) {
    public AddModificationReasonToExecutionCommand {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("Execution ID can't be null");
        }
        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Reason can't be null");
        }
    }
}
