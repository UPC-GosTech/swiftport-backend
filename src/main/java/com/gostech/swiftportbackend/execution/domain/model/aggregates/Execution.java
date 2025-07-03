package com.gostech.swiftportbackend.execution.domain.model.aggregates;

import com.gostech.swiftportbackend.execution.domain.model.commands.CreateExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Entity
public class Execution extends AuditableAbstractAggregateRoot<Execution> {

    private TaskExecutionStatus taskExecutionStatus;

    @Embedded
    private ExecutionTimeFrame executionTimeFrame;

    @OneToMany(mappedBy = "execution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncidentReport> incidents;

    private String modificationReason;

    @Embedded
    private TenantId tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_programming_id", referencedColumnName = "id")
    private TaskProgramming taskProgramming;

    public Execution() {
        this.incidents = new ArrayList<>();
    }

    public Execution(String taskExecutionStatus, LocalDateTime start, LocalDateTime end, Long tenantId) {
        switch (taskExecutionStatus) {
            case "Completed":
                this.taskExecutionStatus = TaskExecutionStatus.COMPLETED;
                break;
            case "InProgress":
                this.taskExecutionStatus = TaskExecutionStatus.IN_PROGRESS;
                break;
            case "Cancelled":
                this.taskExecutionStatus = TaskExecutionStatus.CANCELLED;
                break;
            default:
                this.taskExecutionStatus = TaskExecutionStatus.PENDING;
                break;
        }
        this.executionTimeFrame = new ExecutionTimeFrame(start, end);
        this.incidents = new ArrayList<>();
        this.modificationReason = "";
        this.tenantId = new TenantId(tenantId);
    }

    public Execution(CreateExecutionCommand  command) {
        switch (command.taskExecutionStatus()) {
            case "Completed":
                this.taskExecutionStatus = TaskExecutionStatus.COMPLETED;
                break;
            case "InProgress":
                this.taskExecutionStatus = TaskExecutionStatus.IN_PROGRESS;
                break;
            case "Cancelled":
                this.taskExecutionStatus = TaskExecutionStatus.CANCELLED;
                break;
            default:
                this.taskExecutionStatus = TaskExecutionStatus.PENDING;
                break;
        }
        this.executionTimeFrame = new ExecutionTimeFrame(command.start(), command.end());
        this.incidents = new ArrayList<>();
        this.modificationReason = "";
        this.tenantId = new TenantId(command.tenantId());
    }

    public void addIncidentReport(IncidentReport incidentReport) {
        incidentReport.setExecution(this);
        this.incidents.add(incidentReport);
    }

    public void updateTaskExecutionStatus(String taskExecutionStatus) {
        switch (taskExecutionStatus) {
            case "Completed":
                this.taskExecutionStatus = TaskExecutionStatus.COMPLETED;
                break;
            case "InProgress":
                this.taskExecutionStatus = TaskExecutionStatus.IN_PROGRESS;
                break;
            case "Cancelled":
                this.taskExecutionStatus = TaskExecutionStatus.CANCELLED;
                break;
            default:
                this.taskExecutionStatus = TaskExecutionStatus.PENDING;
                break;
        }
    }

    public void addModifications(UpdateExecutionCommand command) {
        updateTaskExecutionStatus(command.taskExecutionStatus());
        this.executionTimeFrame = new ExecutionTimeFrame(command.start(), command.end());
        this.modificationReason = command.reason();
    }
}
