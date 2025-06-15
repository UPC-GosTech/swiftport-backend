package com.gostech.swiftportbackend.iam.domain.model.queries;

/**
 * Query to get a user by ID
 * @param userId the user ID
 */
public record GetUserByIdQuery(Long userId) {
    public GetUserByIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
        }
    }
} 