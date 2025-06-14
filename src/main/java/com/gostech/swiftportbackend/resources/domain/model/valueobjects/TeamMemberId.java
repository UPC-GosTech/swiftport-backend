package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TeamMemberId(Long teamMemberId) {
    public TeamMemberId {
        if (teamMemberId == null || teamMemberId <= 0) {
            throw new IllegalArgumentException("Team member Id cannot be null or zero or negative");
        }
    }
}
