package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record UpdateEquipmentStatusResource(String status) {
    public UpdateEquipmentStatusResource {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
