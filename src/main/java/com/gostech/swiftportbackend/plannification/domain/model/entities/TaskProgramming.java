package com.gostech.swiftportbackend.plannification.domain.model.entities;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ProgrammingStatus;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ResourceReference;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TimeInterval;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
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

    public TaskProgramming(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end, String programmingStatus) {
        this.resourceReference = new ResourceReference(resourceType, resourceId);
        this.timeInterval = new TimeInterval(start, end);
        switch (programmingStatus) {
            case "Pending":
                this.programmingStatus = ProgrammingStatus.PENDING;
                break;
            case "Completed":
                this.programmingStatus = ProgrammingStatus.COMPLETED;
                break;
            case "InProgress":
                this.programmingStatus = ProgrammingStatus.IN_PROGRESS;
                break;
            default:
                this.programmingStatus = ProgrammingStatus.CANCELLED;
                break;
        }
    }

    public TaskProgramming() {}

    public boolean overlaps(TimeInterval other) {
        return timeInterval.overlaps(other);
    }

    public void updateStatus(ProgrammingStatus newStatus) {
        this.programmingStatus = newStatus;
    }

}
