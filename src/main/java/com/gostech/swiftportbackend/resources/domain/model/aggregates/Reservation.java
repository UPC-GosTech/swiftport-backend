package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TimeInterval;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "resourceId", column = @Column(name = "resource_id_ref")),
            @AttributeOverride(name = "resourceType", column = @Column(name = "resource_type_ref"))
    })
    private ResourceReference resourceReference;

    @Embedded
    private TimeInterval timeInterval;

    @Embedded
    private TenantId tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Equipment equipment;

    public Reservation(Long tenantId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
        switch (resourceType) {
            case "Vehicle":
                this.resourceReference = new ResourceReference("Vehicle", resourceId);
                break;
            case "Employee":
                this.resourceReference = new ResourceReference("Employee", resourceId);
                break;
            case "Position":
                this.resourceReference = new ResourceReference("Position", resourceId);
                break;
            case "Zone":
                this.resourceReference = new ResourceReference("Zone", resourceId);
                break;
            case "Equipment":
                this.resourceReference = new ResourceReference("Equipment", resourceId);
                break;
            default:
                this.resourceReference = new ResourceReference("Team", resourceId);
                break;
        }
        this.timeInterval = new TimeInterval(start, end);
        this.tenantId = new TenantId(tenantId);
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
            default:
                this.resourceReference = new ResourceReference(ResourceType.TEAM.toString(), command.resourceId());
                break;
        }
        this.timeInterval = new TimeInterval(command.start(), command.end());
        this.tenantId =  new TenantId(tenantId);
    }

    public boolean overlaps(TimeInterval other) {
        return this.timeInterval.overlaps(other);
    }

    public void setTimeInterval(LocalDateTime start, LocalDateTime end) {
        this.timeInterval = new TimeInterval(start, end);
    }

}
