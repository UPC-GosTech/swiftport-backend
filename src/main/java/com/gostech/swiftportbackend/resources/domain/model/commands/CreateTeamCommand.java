package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TeamId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;

import java.time.LocalDateTime;
import java.util.List;

public record CreateTeamCommand(TeamId teamId, TenantId tenantId, String name, List<EmployeeId> memberIds, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public CreateTeamCommand {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (memberIds == null || memberIds.isEmpty()) {
            throw new IllegalArgumentException("memberIds cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
    }
}
