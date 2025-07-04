package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record CreateTeamMemberResource(Long employeeId) {
    public CreateTeamMemberResource {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("employeeId cannot be null");
        }
    }
}
