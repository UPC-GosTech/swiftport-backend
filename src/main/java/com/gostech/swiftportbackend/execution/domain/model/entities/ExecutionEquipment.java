package com.gostech.swiftportbackend.execution.domain.model.entities;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExecutionEquipment extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "execution_id")
    private Execution execution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    public ExecutionEquipment() {}

    public ExecutionEquipment(Execution execution, Equipment equipment) {
        this.execution = execution;
        this.equipment = equipment;
    }
} 