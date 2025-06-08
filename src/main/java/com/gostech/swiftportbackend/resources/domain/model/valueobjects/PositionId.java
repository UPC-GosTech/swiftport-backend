package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PositionId(Long positionId) {
    public PositionId {
        if (positionId == null || positionId <= 1) {
            throw new IllegalArgumentException("PositionId cannot be null or less than 1");
        }
    }
}
