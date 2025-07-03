package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.iam.domain.model.aggregates.Tenant;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Embedded
    private TenantId tenantId;

    @Embedded
    @AttributeOverride(name = "resourceId", column = @Column(name = "resource_id_ref"))
    private ResourceReference resourceReference;

    @Embedded
    private TimeInterval timeInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Equipment equipment;

    public Reservation(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
        switch (resourceType) {
            case "Vehicle":
                this.resourceReference = new ResourceReference(ResourceType.VEHICLE.toString(), resourceId);
                break;
            case "Employee":
                this.resourceReference = new ResourceReference(ResourceType.EMPLOYEE.toString(), resourceId);
                break;
            case "Position":
                this.resourceReference = new ResourceReference(ResourceType.POSITION.toString(), resourceId);
                break;
            case "Zone":
                this.resourceReference = new ResourceReference(ResourceType.ZONE.toString(), resourceId);
                break;
            case "Equipment":
                this.resourceReference = new ResourceReference(ResourceType.EQUIPMENT.toString(), resourceId);
                break;
            case "Team":
                this.resourceReference = new ResourceReference(ResourceType.TEAM.toString(), resourceId);
                break;
            default:
                this.resourceReference = null;
                break;
        }
        this.timeInterval = new TimeInterval(start, end);
    }

    public Reservation() {}

    public Reservation(Long tenantId, CreateReservationCommand command) {
        switch (command.resourceType()) {
            case "Vehicle":
                this.resourceReference = new ResourceReference(ResourceType.VEHICLE.toString(), command.resourceId());
                break;
            case "Employee":
                this.resourceReference = new ResourceReference(ResourceType.EMPLOYEE.toString(), command.resourceId());
                break;
            case "Position":
                this.resourceReference = new ResourceReference(ResourceType.POSITION.toString(), command.resourceId());
                break;
            case "Zone":
                this.resourceReference = new ResourceReference(ResourceType.ZONE.toString(), command.resourceId());
                break;
            case "Equipment":
                this.resourceReference = new ResourceReference(ResourceType.EQUIPMENT.toString(), command.resourceId());
                break;
            case "Team":
                this.resourceReference = new ResourceReference(ResourceType.TEAM.toString(), command.resourceId());
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
