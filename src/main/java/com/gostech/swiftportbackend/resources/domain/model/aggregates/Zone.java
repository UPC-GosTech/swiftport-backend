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

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locationsList;

    public Zone(Long tenantId, String name) {
        this.name = name;
        this.locationsList = new ArrayList<>();
    }

    public Zone() {
        this.locationsList = new ArrayList<>();
    }

    public Zone(Long tenantId, CreateZoneCommand command) {
        this.name = command.name();
        this.locationsList = new ArrayList<>();
    }

    public void addLocation(Location location) {
        location.setZone(this);
        this.locationsList.add(location);
    }
}
