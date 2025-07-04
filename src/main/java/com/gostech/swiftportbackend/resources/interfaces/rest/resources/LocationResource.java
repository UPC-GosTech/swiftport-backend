package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;

public record LocationResource(Long id, Long zoneId, String street, String city, String country, Double latitude, Double longitude, Availability status) {
}
