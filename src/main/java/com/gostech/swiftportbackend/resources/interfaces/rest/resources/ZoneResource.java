package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.List;

public record ZoneResource(Long zoneId, Long tenantId, String name) {
}
