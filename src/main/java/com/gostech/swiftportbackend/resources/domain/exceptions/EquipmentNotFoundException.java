package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(Long equipmentId) {
        super("Equipment with id " + equipmentId + " not found");
    }
}
