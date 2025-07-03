package com.gostech.swiftportbackend.iam.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

/**
 * Update User Status Resource
 * @param active the new active status for the user
 */
public record UpdateUserStatusResource(
    @NotNull(message = "Active status is required")
    Boolean active
) {
} 