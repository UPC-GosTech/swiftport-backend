package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.LocationId;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Location extends AuditableModel {
    /**
     * class Location <<Entity>> {
     *         - LocationId id
     *         - Address address
     *         - Coordinates coordinates
     *         - LocalDateTime createdAt
     *         - LocalDateTime updatedAt
     *       }
     */

    @NotNull
    @Embedded
    @Column(name = "location_id")
    private LocationId locationId;
}
