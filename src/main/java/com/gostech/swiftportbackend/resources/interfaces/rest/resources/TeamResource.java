package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.List;

public record TeamResource(Long teamId, Long tenantId, String name) {
}
