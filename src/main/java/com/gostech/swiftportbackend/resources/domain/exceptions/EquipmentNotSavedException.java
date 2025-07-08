package com.gostech.swiftportbackend.resources.domain.exceptions;

public class EquipmentNotSavedException extends RuntimeException {
    public EquipmentNotSavedException(String message) {
        super("Error saving equipment: " + message);
    }
}
