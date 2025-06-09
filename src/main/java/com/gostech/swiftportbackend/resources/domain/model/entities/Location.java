package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Address;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Coordinates;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.LocationId;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Location extends AuditableModel {
    @NotNull
    @Embedded
    private LocationId locationId;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Embedded
    private Address address;

    @Embedded
    private Coordinates coordinates;
}
