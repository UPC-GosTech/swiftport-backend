package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record LocationResource(Long zoneId, String street, String city, String country, Double latitude, Double longitude) {
}
