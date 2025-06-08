package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TaskId(Long taskId) {
    public TaskId {
        if (taskId == null || taskId < 1) {
            throw  new IllegalArgumentException("TaskId cannot be null or less than 1");
        }
    }
}
