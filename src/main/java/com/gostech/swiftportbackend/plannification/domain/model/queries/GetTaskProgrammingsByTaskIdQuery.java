package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTaskProgrammingsByTaskIdQuery(Long taskId) {
    public GetTaskProgrammingsByTaskIdQuery {
        if (taskId == null || taskId <= 0) {
            throw new IllegalArgumentException("task id cannot be null or negative");
        }
    }
}
