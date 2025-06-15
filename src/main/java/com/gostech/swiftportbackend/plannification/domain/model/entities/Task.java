package com.gostech.swiftportbackend.plannification.domain.model.entities;

import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.TaskStatus;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Task extends AuditableModel {

    private String description;

    @Embedded
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskProgramming> taskProgrammings;

    public Task() {
        this.taskProgrammings = new ArrayList<>();
    }

    public Task(String description, TaskStatus status) {
        this.description = description;
        this.status = status;
        this.taskProgrammings = new ArrayList<>();
    }

    public void addProgramming(TaskProgramming programming) {
        programming.setTask(this);
        taskProgrammings.add(programming);
    }
}
