package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreatePositionCommand(String title, String description) {
    public CreatePositionCommand {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
