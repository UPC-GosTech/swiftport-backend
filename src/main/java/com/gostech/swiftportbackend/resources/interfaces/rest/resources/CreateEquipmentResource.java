package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import java.math.BigDecimal;

public record CreateEquipmentResource(String name, String status, String code, String plate, BigDecimal capacityLoad, Integer capacityPax) {
    public  CreateEquipmentResource {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null");
        }
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("code cannot be null or empty");
        }
        if (plate == null || plate.isEmpty()) {
            throw new IllegalArgumentException("plate cannot be null or empty");
        }
        if (capacityLoad == null || capacityLoad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("capacityLoad cannot be null or empty");
        }
        if (capacityPax == null || capacityPax < 0) {
            throw new IllegalArgumentException("capacityPax cannot be null or empty");
        }
    }
}