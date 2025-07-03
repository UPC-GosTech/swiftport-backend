package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.List;

public record CreateZoneCommand(String name) {
    public CreateZoneCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
