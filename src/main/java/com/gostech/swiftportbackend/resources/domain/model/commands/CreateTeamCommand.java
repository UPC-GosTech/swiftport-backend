package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.List;

public record CreateTeamCommand(String name) {
    public CreateTeamCommand {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
    }
}
