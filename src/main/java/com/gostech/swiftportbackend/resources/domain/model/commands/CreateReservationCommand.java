package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;

import java.time.LocalDateTime;

public record CreateReservationCommand(ReservationId reservationId, TenantId tenantId, ResourceType resourceType, ResourceId resourceId, TimeInterval timeInterval, ActivityId activityId, TaskId taskId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public CreateReservationCommand {
        if (reservationId == null) {
            throw new IllegalArgumentException("reservationId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (resourceType == null) {
            throw new IllegalArgumentException("resourceType cannot be null");
        }
        if (resourceId == null) {
            throw new IllegalArgumentException("resourceId cannot be null");
        }
        if (timeInterval == null) {
            throw new IllegalArgumentException("timeInterval cannot be null");
        }
        if (activityId == null) {
            throw new IllegalArgumentException("activityId cannot be null");
        }
        if (taskId == null) {
            throw new IllegalArgumentException("taskId cannot be null");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
    }
}
