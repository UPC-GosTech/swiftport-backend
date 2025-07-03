package com.gostech.swiftportbackend.resources.domain.model.commands;

public record AddLocationCommand(Long zoneId, String street, String city, String country, Double latitude, Double longitude, String locationStatus) {
    public AddLocationCommand {
        if (zoneId == null || zoneId <= 0) {
            throw new IllegalArgumentException("Zone id cannot be null or less than zero");
        }
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (latitude == null) {
            throw new IllegalArgumentException("Latitude must not be null");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("Longitude must not be null");
        }
        if (locationStatus == null || locationStatus.isEmpty()) {
            throw new IllegalArgumentException("LocationStatus must not be null or empty");
        }
    }
}
