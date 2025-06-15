package com.gostech.swiftportbackend.plannification.domain.model.commands;

import java.time.LocalDateTime;

public record CreateActivityCommand(String activityCode, String description, LocalDateTime expectedTime, Integer weekNumber, String activityStatus, Long zoneOrigin, Long locationOrigin, Long zoneDestination, Long locationDestination, Long tenantId) {
    public CreateActivityCommand {
        if (activityCode == null || activityCode.isEmpty()) {
            throw new IllegalArgumentException("ActivityCode cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (expectedTime == null || expectedTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("ExpectedTime cannot be null or empty");
        }
        if (weekNumber == null || weekNumber < 0) {
            throw new IllegalArgumentException("WeekNumber cannot be null or less than 0");
        }
        if (activityStatus == null || activityStatus.isEmpty()) {
            throw new IllegalArgumentException("ActivityStatus cannot be null or empty");
        }
        if (zoneOrigin == null || zoneOrigin < 0) {
            throw new IllegalArgumentException("ZoneOrigin cannot be null or less than 0");
        }
        if (locationOrigin == null || locationOrigin < 0) {
            throw new IllegalArgumentException("LocationOrigin cannot be null or less than 0");
        }
        if (zoneDestination == null || zoneDestination < 0) {
            throw new IllegalArgumentException("ZoneDestination cannot be null or less than 0");
        }
        if (locationDestination == null || locationDestination < 0) {
            throw new IllegalArgumentException("LocationDestination cannot be null or less than 0");
        }
        if (tenantId == null || tenantId < 0) {
            throw new IllegalArgumentException("TenantId cannot be null or less than 0");
        }
    }
}
