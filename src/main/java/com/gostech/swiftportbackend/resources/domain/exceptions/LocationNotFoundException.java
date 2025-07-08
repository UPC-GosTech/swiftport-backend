package com.gostech.swiftportbackend.resources.domain.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long locationId) {
        super("Location with id " + locationId + " not found");
    }
}
