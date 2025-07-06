package com.gostech.swiftportbackend.plannification.domain.model.entities;

import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskProgrammingCommand;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ProgrammingStatus;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ReservationId;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class TaskProgramming extends AuditableModel {

    @Embedded
    private ReservationId reservationId;

    private ProgrammingStatus programmingStatus;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @NotNull
    private Task task;

    public TaskProgramming(Long reservationId, String programmingStatus) {
        this.reservationId = new ReservationId(reservationId);
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

    public TaskProgramming(Long reservationId, AddTaskProgrammingCommand command) {
        this.reservationId = new ReservationId(reservationId);
        switch (command.programmingStatus()) {
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

    public void updateStatus(String newStatus) {
        switch (newStatus) {
            case "Pending":
                this.programmingStatus = ProgrammingStatus.PENDING;
                break;
            case "Completed":
                this.programmingStatus = ProgrammingStatus.COMPLETED;
                break;
            case "InProgress":
                this.programmingStatus = ProgrammingStatus.IN_PROGRESS;
                break;
            case "Cancelled":
                this.programmingStatus = ProgrammingStatus.CANCELLED;
                break;
            default:
                break;
        }
    }

}
