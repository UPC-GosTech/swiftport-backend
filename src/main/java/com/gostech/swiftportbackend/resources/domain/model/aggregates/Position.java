package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.PositionId;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Position extends AuditableAbstractAggregateRoot<Position> {

    /**
     *      class Equipment <<AggregateRoot>> {
     *         - EquipmentId id
     *         - TenantId tenantId
     *         - String name
     *         - EquipmentStatus status
     *         - LocalDateTime createdAt
     *         - LocalDateTime updatedAt
     *         # Equipment(CreateEquipmentCommand)
     *         + boolean isAvailable(TimeInterval interval)
     *         + void scheduleInspection(LocalDateTime date)
     *       }
     */
    @Embedded
    private PositionId positionId;

    @Embedded
    private TenantId tenantId;

    private String title;
    private String description;

    public Position(Long positionId, Long tenantId, String title, String description) {
        this.positionId = new PositionId(positionId);
        this.tenantId = new TenantId(tenantId);
        this.title = title;
        this.description = description;
    }

    public Position() {}

    public Position(CreatePositionCommand command) {
        this.positionId = new PositionId(command.positionId());
        this.tenantId = new TenantId(command.tenantId());
        this.title = command.title();
        this.description = command.description();
    }
}
