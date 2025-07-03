package com.gostech.swiftportbackend.resources.domain.model.commands;

public record UpdateLocationStatusCommand(Long locationId, String status) {
    public UpdateLocationStatusCommand {
        if (locationId == null || locationId <= 0) {
            throw new IllegalArgumentException("Location ID cannot be null or less than zero");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
