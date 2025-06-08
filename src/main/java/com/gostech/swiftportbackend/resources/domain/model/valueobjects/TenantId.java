package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TenantId(Long tenantId) {
    public TenantId {
        if (tenantId == null || tenantId < 1) {
            throw new IllegalArgumentException("TenantId cannot be null or less than 1");
        }
    }
}
