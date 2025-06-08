package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateVehicleCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    @Embedded
    private VehicleId vehicleId;

    @Embedded
    private TenantId tenantId;

    private String plateNumber;
    private String type;

    @Embedded
    private Capacity capacity;

    @Embedded
    private VehicleStatus vehicleStatus;

    public Vehicle(Long vehicleId, Long tenantId, String plateNumber, String type, BigDecimal tons, Integer passengers, String status) {
        this.vehicleId = new VehicleId(vehicleId);
        this.tenantId = new TenantId(tenantId);
        this.plateNumber = plateNumber;
        this.type = type;
        this.capacity = new Capacity(tons, passengers);
        switch (status) {
            case "Available":
                this.vehicleStatus = VehicleStatus.AVAILABLE;
                break;
            case "In Maintenance":
                this.vehicleStatus = VehicleStatus.IN_MAINTENANCE;
                break;
            default:
                this.vehicleStatus = VehicleStatus.UNAVAILABLE;
                break;
        }
    }

    public Vehicle() {}

    public Vehicle(CreateVehicleCommand command) {
        this.vehicleId = new VehicleId(command.vehicleId());
        this.tenantId = new TenantId(command.tenantId());
        this.plateNumber = command.plateNumber();
        this.type = command.type();
        this.capacity = new Capacity(command.tons(), command.passengers());
    }

    public boolean isAvailable(TimeInterval timeInterval) {
        return this.vehicleStatus == VehicleStatus.AVAILABLE;

        /**
         * MISSING PART OF THE LOGIC
         */

    }

    public void scheduleMaintenance(LocalDateTime scheduledTime) {
        this.vehicleStatus = VehicleStatus.IN_MAINTENANCE;

        /**
         * MISSING PART OF THE LOGIC
         */
    }
}
