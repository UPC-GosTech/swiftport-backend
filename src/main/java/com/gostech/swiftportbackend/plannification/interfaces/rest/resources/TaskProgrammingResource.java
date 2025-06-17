package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import java.time.LocalDateTime;

public record TaskProgrammingResource(Long taskProgrammingId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end, String programmingStatus) {
}
