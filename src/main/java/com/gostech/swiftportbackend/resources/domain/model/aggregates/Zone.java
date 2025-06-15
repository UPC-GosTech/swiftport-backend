package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.LocationsList;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Zone extends AuditableAbstractAggregateRoot<Zone> {

    @Embedded
    private TenantId tenantId;

    private String name;

    @Embedded
    private LocationsList locationsList;

    public Zone(Long tenantId, String name) {
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.locationsList = new LocationsList();
    }

    public Zone() {}

    public Zone(CreateZoneCommand command) {
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        this.locationsList = new LocationsList();

        /**
         * MISSING ADD LOCATION
         *      ON CONSTRUCTOR
         *      AS FUNCTION
         */
    }
}
