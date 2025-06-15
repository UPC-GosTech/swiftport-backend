package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Zone extends AuditableAbstractAggregateRoot<Zone> {

    @Embedded
    private TenantId tenantId;

    private String name;

    @ElementCollection
    @CollectionTable(name = "zone_locations", joinColumns = @JoinColumn(name = "zone_id"))
    private List<Location> locationsList = new ArrayList<>();

    public Zone(Long tenantId, String name) {
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.locationsList = new ArrayList<>();
    }

    public Zone() {}

    public Zone(CreateZoneCommand command) {
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        this.locationsList = command.locations();

        /**
         * MISSING ADD LOCATION
         *      ON CONSTRUCTOR
         *      AS FUNCTION
         */
    }
}
