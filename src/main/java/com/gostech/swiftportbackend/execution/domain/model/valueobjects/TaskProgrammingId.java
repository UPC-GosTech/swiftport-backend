package com.gostech.swiftportbackend.execution.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TaskProgrammingId(Long taskProgrammingId) {
    public TaskProgrammingId {
        if (taskProgrammingId == null || taskProgrammingId <= 0) {
            throw new IllegalArgumentException("taskProgrammingId can't be null or empty");
        }
    }
}
