package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TeamId(Long teamId) {
    public TeamId {
        if (teamId == null || teamId < 1) {
            throw new IllegalArgumentException("TeamId cannot be null or less than 1");
        }
    }
}
