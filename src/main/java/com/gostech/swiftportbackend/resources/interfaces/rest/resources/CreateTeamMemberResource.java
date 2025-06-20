package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record CreateTeamMemberResource(Long teamId, Long employeeId) {
    public CreateTeamMemberResource {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("employeeId cannot be null");
        }
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("teamId cannot be null");
        }
    }
}
