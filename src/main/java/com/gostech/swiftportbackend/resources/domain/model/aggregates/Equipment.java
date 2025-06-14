package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Equipment extends AuditableAbstractAggregateRoot<Equipment> {

    @Embedded
    private EquipmentId equipmentId;

    @Embedded
    private TenantId tenantId;

    private String name;

    @Embedded
    EquipmentStatus equipmentStatus;

    public Equipment(Long EquipmentId, Long tenantId, String name, String status) {
        this.equipmentId = new EquipmentId(EquipmentId);
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        switch (status) {
            case "Available":
                this.equipmentStatus = EquipmentStatus.AVAILABLE;
                break;
            case "In Maintenance":
                this.equipmentStatus = EquipmentStatus.IN_MAINTENANCE;
                break;
            default:
                this.equipmentStatus = EquipmentStatus.UNAVAILABLE;
                break;
        }
    }

    public Equipment() {}

    public Equipment(CreateEquipmentCommand command) {
        this.equipmentId = new EquipmentId(command.equipmentId());
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        switch (command.status()) {
            case "Available":
                this.equipmentStatus = EquipmentStatus.AVAILABLE;
                break;
            case "In Maintenance":
                this.equipmentStatus = EquipmentStatus.IN_MAINTENANCE;
                break;
            default:
                this.equipmentStatus = EquipmentStatus.UNAVAILABLE;
                break;
        }
    }

    public boolean isAvailable(TimeInterval timeInterval) {
        return this.equipmentStatus == EquipmentStatus.AVAILABLE;

        /**
         * MISSING PART OF THE LOGIC
         */

    }

    public void scheduleInspection(LocalDateTime scheduledTime) {
        this.equipmentStatus = EquipmentStatus.IN_MAINTENANCE;

        /**
         * MISSING PART OF THE LOGIC
         */
    }
}
