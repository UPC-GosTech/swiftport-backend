package com.gostech.swiftportbackend.plannification.domain.model.entities;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ProgrammingStatus;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ResourceReference;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TimeInterval;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class TaskProgramming extends AuditableModel {

    @Embedded
    private ResourceReference resourceReference;

    @Embedded
    private TimeInterval timeInterval;

    @Embedded
    private ProgrammingStatus programmingStatus;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @NotNull
    private Task task;

    public TaskProgramming(ResourceReference resourceReference, TimeInterval timeInterval, ProgrammingStatus programmingStatus, Task task) {
        this.resourceReference = resourceReference;
        this.timeInterval = timeInterval;
        this.programmingStatus = programmingStatus;
        this.task = task;
    }

    public boolean overlaps(TimeInterval other) {
        return timeInterval.overlaps(other);
    }

    public void updateStatus(ProgrammingStatus newStatus) {
        this.programmingStatus = newStatus;
    }

}
