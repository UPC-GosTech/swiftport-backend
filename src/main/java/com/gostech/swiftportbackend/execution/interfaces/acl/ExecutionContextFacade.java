package com.gostech.swiftportbackend.execution.interfaces.acl;

import java.time.LocalDateTime;

public interface ExecutionContextFacade {
    Long createExecution(Long taskProgrammingId, String taskExecutionStatus, LocalDateTime start, LocalDateTime end, Long tenantId);
}
