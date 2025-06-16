package com.gostech.swiftportbackend.plannification.domain.model.entities;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskCommand;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.TaskStatus;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Task extends AuditableModel {

    private String description;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    @NotNull
    private Activity activity;

    @Embedded
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskProgramming> taskProgrammings;

    public Task() {
        this.taskProgrammings = new ArrayList<>();
    }

    public Task(String description, String status) {
        this.description = description;
        switch (status) {
            case "Pending":
                this.status = TaskStatus.PENDING;
                break;
            case "Completed":
                this.status = TaskStatus.COMPLETED;
                break;
            case "InProgress":
                this.status = TaskStatus.IN_PROGRESS;
                break;
            default:
                this.status = TaskStatus.CANCELLED;
                break;
        }
        this.taskProgrammings = new ArrayList<>();
    }

    public Task(AddTaskCommand command) {
        this.description = command.description();
        switch (command.status()) {
            case "Pending":
                this.status = TaskStatus.PENDING;
                break;
            case "Completed":
                this.status = TaskStatus.COMPLETED;
                break;
            case "InProgress":
                this.status = TaskStatus.IN_PROGRESS;
                break;
            default:
                this.status = TaskStatus.CANCELLED;
                break;
        }
        this.taskProgrammings = new ArrayList<>();
    }

    public void addProgramming(TaskProgramming programming) {
        programming.setTask(this);
        taskProgrammings.add(programming);
    }
}
