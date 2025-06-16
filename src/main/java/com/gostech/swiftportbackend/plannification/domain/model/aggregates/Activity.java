package com.gostech.swiftportbackend.plannification.domain.model.aggregates;

import com.gostech.swiftportbackend.plannification.domain.model.commands.CreateActivityCommand;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActicityCode;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActivityStatus;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.LocationRef;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Activity extends AuditableAbstractAggregateRoot<Activity> {

    @Embedded
    private ActicityCode acticityCode;

    private String description;

    @Column(name = "expected_time")
    private LocalDateTime expectedTime;

    private Integer weekNumber;

    @Embedded
    private ActivityStatus activityStatus;

    @Embedded
    private LocationRef origin;

    @Embedded
    private LocationRef destination;

    @Embedded
    private TenantId tenantId;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Activity(String activityCode, String description, LocalDateTime expectedTime, Integer weekNumber, String activityStatus, Long zoneOrigin, Long locationOrigin, Long zoneDestination, Long locationDestination, Long tenantId) {
        this.acticityCode = new ActicityCode(activityCode);
        this.description = description;
        this.expectedTime = expectedTime;
        this.weekNumber = weekNumber;
        switch (activityStatus) {
            case "Planned":
                this.activityStatus = ActivityStatus.PLANNED;
                break;
            case "Completed":
                this.activityStatus = ActivityStatus.COMPLETED;
                break;
            case "InProgress":
                this.activityStatus = ActivityStatus.IN_PROGRESS;
                break;
            default:
                this.activityStatus = ActivityStatus.CANCELLED;
                break;
        }
        this.origin = new LocationRef(zoneOrigin, locationOrigin);
        this.destination = new LocationRef(zoneDestination, locationDestination);
        this.tenantId = new TenantId(tenantId);
        this.tasks = new ArrayList<>();
    }

    public Activity() {
        this.tasks = new ArrayList<>();
    }

    public Activity(CreateActivityCommand command) {
        this.acticityCode = new ActicityCode(command.activityCode());
        this.description = command.description();
        this.expectedTime = command.expectedTime();
        this.weekNumber = command.weekNumber();
        switch (command.activityStatus()) {
            case "Planned":
                this.activityStatus = ActivityStatus.PLANNED;
                break;
            case "Completed":
                this.activityStatus = ActivityStatus.COMPLETED;
                break;
            case "InProgress":
                this.activityStatus = ActivityStatus.IN_PROGRESS;
                break;
            default:
                this.activityStatus = ActivityStatus.CANCELLED;
                break;
        }
        this.origin = new LocationRef(command.zoneOrigin(), command.locationOrigin());
        this.destination = new LocationRef(command.zoneDestination(), command.locationDestination());
        this.tenantId = new TenantId(command.tenantId());
    }

    public void addTask(Task task) {
        task.setActivity(this);
        tasks.add(task);
    }

}
