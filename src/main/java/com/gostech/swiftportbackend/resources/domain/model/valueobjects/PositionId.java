package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PositionId(Long positionId) {
    public PositionId {
        if (positionId <= 0) {
            throw new IllegalArgumentException("Position if cannot be zero or less than zero");
        }
    }
}
