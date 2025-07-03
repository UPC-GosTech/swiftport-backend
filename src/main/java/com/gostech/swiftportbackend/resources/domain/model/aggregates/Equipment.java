package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Equipment extends AuditableAbstractAggregateRoot<Equipment> {

    @Embedded
    private TenantId tenantId;

    private String code;
    private String name;
    private String plate;

    @Embedded
    Capacity capacity;

    Availability equipmentStatus;

    public Equipment(String name, String status, String code, String plate, BigDecimal capacityLoad, Integer capacityPax) {
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

    public Equipment(Long TenantId, CreateEquipmentCommand command) {
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

    public void updateEquipmentStatus(String status) {
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
            case "Unavailable":
                this.equipmentStatus = Availability.UNAVAILABLE;
                break;
            default:
                break;
        }
    }
}
