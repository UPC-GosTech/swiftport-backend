package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import lombok.Getter;

@Getter
@Entity
public class Position extends AuditableAbstractAggregateRoot<Position> {

    @Embedded
    private TenantId tenantId;

    private String title;
    private String description;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private java.util.List<Employee> employees;

    public Position(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Position() {}

    public Position(Long tenantId, CreatePositionCommand command) {
        this.title = command.title();
        this.description = command.description();
    }

    public void updateDetails(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
