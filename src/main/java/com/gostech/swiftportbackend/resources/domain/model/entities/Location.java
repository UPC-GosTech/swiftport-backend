package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Address;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Coordinates;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Location extends AuditableModel {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    @NotNull
    private Zone zone;

    @Embedded
    private Address address;

    @Embedded
    private Coordinates coordinates;

    @Embedded
    Availability locationStatus;

    public Location(Address address, Coordinates coordinates, String locationStatus) {
        this.address = address;
        this.coordinates = coordinates;
        switch (locationStatus) {
            case "Available":
                this.locationStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.locationStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.locationStatus = Availability.RESERVED;
                break;
            default:
                this.locationStatus = Availability.UNAVAILABLE;
                break;
        }
    }

    public Location() {}

    public Location(AddLocationCommand command) {
        address = new Address(command.street(), command.city(), command.country());
        coordinates = new Coordinates(command.latitude(), command.longitude());
        switch (command.locationStatus()) {
            case "Available":
                this.locationStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.locationStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.locationStatus = Availability.RESERVED;
                break;
            default:
                this.locationStatus = Availability.UNAVAILABLE;
                break;
        }
    }
}
