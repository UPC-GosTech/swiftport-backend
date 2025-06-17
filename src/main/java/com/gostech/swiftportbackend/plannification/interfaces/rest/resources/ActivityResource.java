package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;

import java.time.LocalDateTime;

public record ActivityResource(Long id, String activityCode, String description, LocalDateTime expectedTime, Integer weekNumber, ActivityStatus activityStatus, Long zoneOrigin, Long locationOrigin, Long zoneDestination, Long locationDestination, Long tenantId) {
}
