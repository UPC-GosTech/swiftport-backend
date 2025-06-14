package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetTeamByIdQuery(Long teamId) {
    public GetTeamByIdQuery {
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("Team id cannot be null or less than 1");
        }
    }
}
