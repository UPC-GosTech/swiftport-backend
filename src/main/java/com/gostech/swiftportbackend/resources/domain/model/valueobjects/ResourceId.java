package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ResourceId(Long resourceId){
    public ResourceId {
        if (resourceId == null || resourceId < 1) {
            throw new IllegalArgumentException("ResourceId cannot be null or less than 1");
        }
    }
}