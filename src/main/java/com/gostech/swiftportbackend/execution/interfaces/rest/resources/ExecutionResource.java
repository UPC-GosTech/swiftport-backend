package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

import com.gostech.swiftportbackend.execution.domain.model.valueobjects.TaskExecutionStatus;

import java.time.LocalDateTime;

public record ExecutionResource(Long id, Long taskProgrammingId, TaskExecutionStatus taskExecutionStatus, LocalDateTime start, LocalDateTime end, String modificationReason, Long tenantId) {
}
