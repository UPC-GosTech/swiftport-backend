package com.gostech.swiftportbackend.shared.domain.exceptions;

public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException() {
        super("Tenant context not found");
    }
}
