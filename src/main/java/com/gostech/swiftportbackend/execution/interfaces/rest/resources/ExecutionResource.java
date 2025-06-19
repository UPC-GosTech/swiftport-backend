package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ExecutionResource(Long id, Long taskProgrammingId, String taskExecutionStatus, LocalDateTime start, LocalDateTime end, String modificationReason, Long tenantId) {
}
