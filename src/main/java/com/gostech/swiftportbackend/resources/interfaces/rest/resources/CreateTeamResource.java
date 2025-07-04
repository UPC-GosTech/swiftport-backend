package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.List;

public record CreateTeamResource(String name) {
    public CreateTeamResource {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
    }
}