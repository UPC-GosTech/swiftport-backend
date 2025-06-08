package com.gostech.swiftportbackend.resources.domain.model.commands;

import java.math.BigDecimal;

public record CreateVehicleCommand(Long vehicleId, Long tenantId, String plateNumber, String type, BigDecimal tons, Integer passengers, String status) {
    public CreateVehicleCommand {
        if (vehicleId == null || vehicleId <= 0) {
            throw new IllegalArgumentException("vehicleId cannot be null");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("plateNumber cannot be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }
        if (tons == null || tons.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("tons cannot be null");
        }
        if (passengers == null || passengers <= 0) {
            throw new IllegalArgumentException("passengers cannot be null or less than 1");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null");
        }
    }
}
