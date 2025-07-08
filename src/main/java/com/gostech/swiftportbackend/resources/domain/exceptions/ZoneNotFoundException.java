package com.gostech.swiftportbackend.resources.domain.exceptions;

public class ZoneNotFoundException extends RuntimeException {
    public ZoneNotFoundException(Long zoneId) {
        super("Zone with id " + zoneId + " not found");
    }
}
