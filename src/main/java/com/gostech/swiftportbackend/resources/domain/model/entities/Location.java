package com.gostech.swiftportbackend.resources.domain.model.entities;

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
    @Column(name = "location_id")
    private LocationId locationId;

    @Embedded
    @JoinColumn(name = "address_id")
    private Address address;

    @Embedded
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @Embedded
    @JoinColumn(name = "created_at_id")
    private LocalDateTime createdAt;

    @Embedded
    @JoinColumn(name = "updated_at_id")
    private LocalDateTime updatedAt;
}
