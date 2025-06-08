package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.LocationsList;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ZoneId;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Zone extends AuditableAbstractAggregateRoot<Zone> {
    @Getter
    @Embedded
    private ZoneId zoneId;

    @Embedded
    private LocationsList locationsList;
}
