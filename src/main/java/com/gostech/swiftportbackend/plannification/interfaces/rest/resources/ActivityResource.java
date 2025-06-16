package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ActivityResource(Long id, String activityCode, String description, LocalDateTime expectedTime, Integer weekNumber, String activityStatus, Long zoneOrigin, Long locationOrigin, Long zoneDestination, Long locationDestination, Long tenantId) {
}
