package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Address;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Coordinates;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Location {

    private Long id;

    @Embedded
    private Address address;

    @Embedded
    private Coordinates coordinates;

    public Location(Address address, Coordinates coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }
}
