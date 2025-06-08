package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;

public record CreateVehicleCommand(VehicleId vehicleId, TenantId tenantId, String plateNumber, String type, Capacity loadCapacity, VehicleStatus status, TimeInterval timeInterval) {
    public CreateVehicleCommand {
        if (vehicleId == null) {
            throw new IllegalArgumentException("vehicleId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("plateNumber cannot be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }
        if (loadCapacity == null) {
            throw new IllegalArgumentException("loadCapacity cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }
        if (timeInterval == null) {
            throw new IllegalArgumentException("timeInterval cannot be null");
        }
    }
}
