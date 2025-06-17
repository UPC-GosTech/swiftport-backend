package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ProgrammingStatus;

import java.time.LocalDateTime;

public record TaskProgrammingResource(Long taskProgrammingId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end, ProgrammingStatus programmingStatus) {
}
