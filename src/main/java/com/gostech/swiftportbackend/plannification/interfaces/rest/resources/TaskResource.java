package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.TaskStatus;

public record TaskResource(Long taskId, String description, TaskStatus status, String title) {
}
