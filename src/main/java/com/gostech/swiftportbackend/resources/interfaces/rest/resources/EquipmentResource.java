package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import java.math.BigDecimal;

public record EquipmentResource(Long equipmentId, Long tenantId, String name, String status, String code, String plate, BigDecimal capacityLoad, Integer capacityPax) {
}
