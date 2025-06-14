package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Embedded
    private ReservationId reservationId;

    @Embedded
    private TenantId tenantId;

    @Embedded
    private ResourceReference resourceReference;

    @Embedded
    private TimeInterval timeInterval;

    public Reservation(Long reservationId, Long tenantId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
        this.reservationId = new ReservationId(reservationId);
        this.tenantId = new TenantId(tenantId);
        switch (resourceType) {
            case "Vehicle":
                this.resourceReference = new ResourceReference(ResourceType.VEHICLE, new ResourceId(resourceId));
                break;
            case "Employee":
                this.resourceReference = new ResourceReference(ResourceType.EMPLOYEE, new ResourceId(resourceId));
                break;
            case "Position":
                this.resourceReference = new ResourceReference(ResourceType.POSITION, new ResourceId(resourceId));
                break;
            case "Zone":
                this.resourceReference = new ResourceReference(ResourceType.ZONE, new ResourceId(resourceId));
                break;
            case "Equipment":
                this.resourceReference = new ResourceReference(ResourceType.EQUIPMENT, new ResourceId(resourceId));
                break;
            case "Team":
                this.resourceReference = new ResourceReference(ResourceType.TEAM, new ResourceId(resourceId));
                break;
            default:
                this.resourceReference = null;
                break;
        }
        this.timeInterval = new TimeInterval(start, end);
    }

    public Reservation() {}

    public Reservation(CreateReservationCommand command) {
        this.reservationId = new ReservationId(command.reservationId());
        this.tenantId = new TenantId(command.tenantId());
        switch (command.resourceType()) {
            case "Vehicle":
                this.resourceReference = new ResourceReference(ResourceType.VEHICLE, new ResourceId(command.resourceId()));
                break;
            case "Employee":
                this.resourceReference = new ResourceReference(ResourceType.EMPLOYEE, new ResourceId(command.resourceId()));
                break;
            case "Position":
                this.resourceReference = new ResourceReference(ResourceType.POSITION, new ResourceId(command.resourceId()));
                break;
            case "Zone":
                this.resourceReference = new ResourceReference(ResourceType.ZONE, new ResourceId(command.resourceId()));
                break;
            case "Equipment":
                this.resourceReference = new ResourceReference(ResourceType.EQUIPMENT, new ResourceId(command.resourceId()));
                break;
            case "Team":
                this.resourceReference = new ResourceReference(ResourceType.TEAM, new ResourceId(command.resourceId()));
                break;
            default:
                this.resourceReference = null;
                break;
        }
        this.timeInterval = new TimeInterval(command.start(), command.end());
    }

    public static Reservation create(CreateReservationCommand command) {
        return new Reservation(command);
    }

    public boolean conflictsWith(TimeInterval other) {
        return this.timeInterval.overlaps(other);
    }

}
