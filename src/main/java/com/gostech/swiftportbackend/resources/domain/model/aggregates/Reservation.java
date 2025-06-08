package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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
    private ResourceType resourceType;

    @Embedded
    private ResourceId resourceId;

    @Embedded
    private TimeInterval timeInterval;

    @Embedded
    private ActivityId activityId;

    @Embedded
    private TaskId taskId;

    public Reservation(Long reservationId, Long tenantId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end, Long activityId, Long taskId) {
        this.reservationId = new ReservationId(reservationId);
        this.tenantId = new TenantId(tenantId);
        switch (resourceType) {
            case "Vehicle":
                this.resourceType = ResourceType.VEHICLE;
                break;
            case "Employee":
                this.resourceType = ResourceType.EMPLOYEE;
                break;
            case "Position":
                this.resourceType = ResourceType.POSITION;
                break;
            case "Zone":
                this.resourceType = ResourceType.ZONE;
                break;
            case "Equipment":
                this.resourceType = ResourceType.EQUIPMENT;
                break;
            case "Team":
                this.resourceType = ResourceType.TEAM;
                break;
            default:
                this.resourceType = null;
                break;
        }
        this.resourceId = new ResourceId(resourceId);
        this.timeInterval = new TimeInterval(start, end);
        this.activityId = new ActivityId(activityId);
        this.taskId = new TaskId(taskId);
    }

    public Reservation() {}

    public Reservation(CreateReservationCommand command) {
        this.reservationId = new ReservationId(command.reservationId());
        this.tenantId = new TenantId(command.tenantId());
        switch (command.resourceType()) {
            case "Vehicle":
                this.resourceType = ResourceType.VEHICLE;
                break;
            case "Employee":
                this.resourceType = ResourceType.EMPLOYEE;
                break;
            case "Position":
                this.resourceType = ResourceType.POSITION;
                break;
            case "Zone":
                this.resourceType = ResourceType.ZONE;
                break;
            case "Equipment":
                this.resourceType = ResourceType.EQUIPMENT;
                break;
            case "Team":
                this.resourceType = ResourceType.TEAM;
                break;
            default:
                this.resourceType = null;
                break;
        }
        this.resourceId = new ResourceId(command.resourceId());
        this.timeInterval = new TimeInterval(command.start(), command.end());
        this.activityId = new ActivityId(command.activityId());
        this.taskId = new TaskId(command.taskId());
    }
}
