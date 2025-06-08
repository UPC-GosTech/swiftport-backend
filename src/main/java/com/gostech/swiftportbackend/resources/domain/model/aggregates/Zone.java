package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.LocationsList;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ZoneId;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Zone extends AuditableAbstractAggregateRoot<Zone> {

    @Embedded
    private ZoneId zoneId;

    @Embedded
    private TenantId tenantId;

    private String name;

    @Embedded
    private LocationsList locationsList;

    public Zone(Long zoneId, Long tenantId, String name) {
        this.zoneId = new ZoneId(zoneId);
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.locationsList = new LocationsList();
    }

    public Zone() {}

    public Zone(CreateZoneCommand command) {
        this.zoneId = new ZoneId(command.zoneId());
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
