package com.gostech.swiftportbackend.iam.domain.model.commands;

/**
 * Update user status command
 * This command is used to update the status (active/inactive) of an existing user
 */
public record UpdateUserStatusCommand(
    Long userId,
    Boolean active
) {
    public UpdateUserStatusCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
        if (active == null) {
            throw new IllegalArgumentException("Active status cannot be null");
        }
    }
} 