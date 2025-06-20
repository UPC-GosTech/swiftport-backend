package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetTeamMemberByIdQuery(Long id) {
    public GetTeamMemberByIdQuery {
        if (id == null || id <= 0 ) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
