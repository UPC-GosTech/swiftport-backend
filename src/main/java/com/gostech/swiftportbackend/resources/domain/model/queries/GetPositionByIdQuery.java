package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetPositionByIdQuery(Long positionId) {
    public GetPositionByIdQuery {
        if (positionId == null || positionId <= 0) {
            throw new IllegalArgumentException("PositionId is required");
        }
    }
}
