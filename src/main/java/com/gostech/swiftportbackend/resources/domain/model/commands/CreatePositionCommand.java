package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.PositionId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;

import java.time.LocalDateTime;

public record CreatePositionCommand(PositionId positionId, TenantId tenantId, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public CreatePositionCommand {
        if (positionId == null) {
            throw new IllegalArgumentException("positionId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
    }
}
