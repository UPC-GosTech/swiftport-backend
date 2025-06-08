package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;

import java.util.List;

public record CreateTeamCommand(Long teamId, Long tenantId, String name, List<EmployeeId> memberIds) {
    public CreateTeamCommand {
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("teamId cannot be null");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (memberIds == null || memberIds.isEmpty()) {
            throw new IllegalArgumentException("memberIds cannot be null or empty");
        }
    }
}
