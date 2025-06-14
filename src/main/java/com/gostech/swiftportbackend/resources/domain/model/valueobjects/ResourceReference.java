package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ResourceReference(String resourceType, Long resourceId) {
    public ResourceReference {
        if (resourceType == null) {
            throw new IllegalArgumentException("resourceType cannot be null");
        }
        if (resourceId == null) {
            throw new IllegalArgumentException("resourceId cannot be null");
        }
    }
}
