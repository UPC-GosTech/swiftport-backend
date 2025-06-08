package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

public record PositionId(Long positionId) {
    public PositionId {
        if (positionId == null || positionId <= 1) {
            throw new NullPointerException("PositionId cannot be null or less than 1");
        }
    }
}
