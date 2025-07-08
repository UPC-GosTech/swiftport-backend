package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EquipmentPlateAlreadyExistsException extends RuntimeException {
    public EquipmentPlateAlreadyExistsException(String message) {
        super("Equipment with this plate already exists: " + message);
    }
}
