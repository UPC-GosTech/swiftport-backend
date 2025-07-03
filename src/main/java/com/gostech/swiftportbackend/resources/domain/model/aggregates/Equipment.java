package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Equipment extends AuditableAbstractAggregateRoot<Equipment> {

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    private String code;
    private String name;
    private String plate;

    @Embedded
    Capacity capacity;

    @Embedded
    Availability equipmentStatus;

    public Equipment(Long tenantId, String name, String status, String code, String plate, BigDecimal capacityLoad, Integer capacityPax) {
        this.tenantId = tenantId;
        this.code = code;
        this.name = name;
        this.plate = plate;
        this.capacity = new Capacity(capacityLoad, capacityPax);
        switch (status) {
            case "Available":
                this.equipmentStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.equipmentStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.equipmentStatus = Availability.RESERVED;
                break;
            default:
                this.equipmentStatus = Availability.UNAVAILABLE;
                break;
        }
    }

    public Equipment() {}

    public Equipment(CreateEquipmentCommand command) {
        this.tenantId = command.tenantId();
        this.code = command.code();
        this.name = command.name();
        this.plate = command.plate();
        this.capacity = new Capacity(command.capacityLoad(), command.capacityPax());
        switch (command.status()) {
            case "Available":
                this.equipmentStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.equipmentStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.equipmentStatus = Availability.RESERVED;
                break;
            default:
                this.equipmentStatus = Availability.UNAVAILABLE;
                break;
        }
    }

    public boolean isAvailable(TimeInterval timeInterval) {
        return this.equipmentStatus == Availability.AVAILABLE;

        /**
         * MISSING PART OF THE LOGIC
         */

    }

    public void scheduleInspection(LocalDateTime scheduledTime) {
        this.equipmentStatus = Availability.VACATION;

        /**
         * MISSING PART OF THE LOGIC
         */
    }
}
