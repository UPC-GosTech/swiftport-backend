package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;

import java.math.BigDecimal;

public record EquipmentResource(Long equipmentId, Long tenantId, String name, Availability status, String code, String plate, BigDecimal capacityLoad, Integer capacityPax) {
}
