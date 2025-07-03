package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateReservationResource(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
    public CreateReservationResource {
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
