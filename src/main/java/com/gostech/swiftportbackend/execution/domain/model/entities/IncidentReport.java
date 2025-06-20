package com.gostech.swiftportbackend.execution.domain.model.entities;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.IncidentSeverity;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class IncidentReport extends AuditableModel {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id")
    @NotNull
    private Execution execution;

    private String title;
    private String description;
    private LocalDateTime reportedAt;

    @Embedded
    private IncidentSeverity severity;

    @Embedded
    private TenantId tenantId;

    public IncidentReport() {}

    public IncidentReport(String title, String description, LocalDateTime reportedAt, String severity, Long tenantId) {
        this.title = title;
        this.description = description;
        this.reportedAt = reportedAt;
        switch (severity) {
            case "Medium":
                this.severity = IncidentSeverity.MEDIUM;
                break;
            case "High":
                this.severity = IncidentSeverity.HIGH;
                break;
            default:
                this.severity = IncidentSeverity.LOW;
                break;
        }
        this.tenantId = new TenantId(tenantId);
    }

    public IncidentReport(AddIncidentReportCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.reportedAt = command.reportedAt();
        switch (command.severity()) {
            case "Medium":
                this.severity = IncidentSeverity.MEDIUM;
                break;
            case "High":
                this.severity = IncidentSeverity.HIGH;
                break;
            default:
                this.severity = IncidentSeverity.LOW;
                break;
        }
        this.tenantId = new TenantId(command.tenantId());
    }

    public void updateDescription(String description) {
        this.description = description;
    }
}
