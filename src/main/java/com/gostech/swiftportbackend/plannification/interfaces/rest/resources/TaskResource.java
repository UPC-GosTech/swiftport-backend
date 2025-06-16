package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record TaskResource(Long taskId, Long activityId, String description, String status, String title) {
}
