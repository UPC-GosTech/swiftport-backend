package com.gostech.swiftportbackend.execution.domain.model.entities;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExecutionEmployee extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id")
    @NotNull
    private Execution execution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    public ExecutionEmployee() {}

    public ExecutionEmployee(Execution execution, Employee employee) {
        this.execution = execution;
        this.employee = employee;
    }
} 