package com.gostech.swiftportbackend.resources.domain.model.commands;

import java.time.LocalDateTime;

public record CreateReservationCommand(Long reservationId, Long tenantId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
    public CreateReservationCommand {
        if (reservationId == null || reservationId <= 0) {
            throw new IllegalArgumentException("reservationId cannot be null");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("resourceType cannot be null");
        }
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("resourceId cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("start cannot be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end cannot be null");
        }
    }
}
