package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreatePositionCommand(Long tenantId, String title, String description) {
    public CreatePositionCommand {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
