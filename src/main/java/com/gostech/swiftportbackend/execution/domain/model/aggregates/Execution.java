package com.gostech.swiftportbackend.execution.domain.model.aggregates;

import com.gostech.swiftportbackend.execution.domain.model.commands.CreateExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Execution extends AuditableAbstractAggregateRoot<Execution> {

    @Embedded
    private TaskProgrammingId taskProgrammingId;

    @Embedded
    private TaskExecutionStatus taskExecutionStatus;

    @Embedded
    private ExecutionTimeFrame executionTimeFrame;

    @OneToMany(mappedBy = "execution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncidentReport> incidents;

    @ElementCollection
    @CollectionTable(name = "execution_employees", joinColumns = @JoinColumn(name = "execution_id"))
    private List<EmployeeId> employeeIdList;

    @ElementCollection
    @CollectionTable(name = "execution_equipment", joinColumns = @JoinColumn(name = "execution_id"))
    private List<EquipmentId> equipmentIdList;

    private String modificationReason;

    @Embedded
    private TenantId tenantId;

    public Execution() {
        this.incidents = new ArrayList<>();
        this.employeeIdList = new ArrayList<>();
        this.equipmentIdList = new ArrayList<>();
    }

    public Execution(Long taskProgrammingId, String taskExecutionStatus, LocalDateTime start, LocalDateTime end, String modificationReason, Long tenantId) {
        this.taskProgrammingId = new TaskProgrammingId(taskProgrammingId);
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
        this.employeeIdList = new ArrayList<>();
        this.equipmentIdList = new ArrayList<>();
        this.modificationReason = "";
        this.tenantId = new TenantId(tenantId);
    }

    public Execution(CreateExecutionCommand  command) {
        this.taskProgrammingId = new TaskProgrammingId(command.taskProgrammingId());
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
        this.employeeIdList = new ArrayList<>();
        this.equipmentIdList = new ArrayList<>();
        this.modificationReason = "";
        this.tenantId = new TenantId(command.tenantId());
    }

    public void addEmployeeId(EmployeeId employeeId) {
        if (!this.employeeIdList.contains(employeeId)) {
            this.employeeIdList.add(employeeId);
        } else throw new IllegalArgumentException("EmployeeId already added");
    }

    public void addEquipmentId(EquipmentId equipmentId) {
        if (!this.equipmentIdList.contains(equipmentId)) {
            this.equipmentIdList.add(equipmentId);
        } else throw new IllegalArgumentException("EquipmentId already added");
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

    public void addModifications(Long taskProgrammingId, String taskExecutionStatus, LocalDateTime start, LocalDateTime end, String modificationReason) {
        this.taskProgrammingId = new TaskProgrammingId(taskProgrammingId);
        updateTaskExecutionStatus(taskExecutionStatus);
        this.executionTimeFrame = new ExecutionTimeFrame(start, end);
        this.modificationReason = modificationReason;
    }

    public void addModifications(UpdateExecutionCommand command) {
        this.taskProgrammingId = new TaskProgrammingId(command.taskProgrammingId());
        updateTaskExecutionStatus(command.taskExecutionStatus());
        this.executionTimeFrame = new ExecutionTimeFrame(command.start(), command.end());
        this.modificationReason = command.reason();
    }
}
