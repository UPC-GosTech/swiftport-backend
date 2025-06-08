package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.PositionId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TimeInterval;

public record CreatePositionCommand(PositionId positionId, TenantId tenantId, String title, String description, TimeInterval timeInterval) {
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
        if (timeInterval == null) {
            throw new IllegalArgumentException("timeInterval cannot be null");
        }
    }
}
